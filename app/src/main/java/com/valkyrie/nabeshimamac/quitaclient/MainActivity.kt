package com.valkyrie.nabeshimamac.quitaclient

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reyurnible.android.xmassplash.XmasSplashActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {


    private var queryEditText: EditText by Delegates.notNull()
    private var listAdapter: ArticleListAdapter by Delegates.notNull()
    private var articleClient: ArticleClient by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(XmasSplashActivity.createIntent(this))
        setContentView(R.layout.activity_main)

        listAdapter = ArticleListAdapter(applicationContext).apply {
            articles = listOf(dummyArticle("Kotlin入門", "たろう"), dummyArticle("Java入門", "じろう"))
        }
        val listView: ListView = findViewById(R.id.list_view) as ListView
        val searchButton = findViewById(R.id.search_button) as ImageButton
        queryEditText = findViewById(R.id.query_edit_text) as EditText

        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.intent(this, article).let {
                startActivity(it)
            }
        }

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        articleClient = retrofit.create(ArticleClient::class.java)
        queryEditText.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId === EditorInfo.IME_ACTION_DONE) {
                //キーボードを非表示
                (this@MainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromInputMethod(queryEditText.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                search(queryEditText.text.toString())
                true
            }
            false
        }
        searchButton.setOnClickListener {
            search(queryEditText.text.toString())
        }
    }

    private fun search(text: String) {
        articleClient.search(text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    queryEditText.text.clear()
                    listAdapter.articles = it
                    listAdapter.notifyDataSetChanged()
                }, {
                    toast("エラー: $it")
                })
    }

    private fun dummyArticle(title: String, userName: String): Article =
            Article(id = "",
                    title = title,
                    url = "https://kotlinlang.org/",
                    user = User(id = "", name = userName, profileImageUrl = ""))


    fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}
