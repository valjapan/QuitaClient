package com.valkyrie.nabeshimamac.quitaclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DialogTitle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(Article("Kotlin入門","たろう"),
                Article("Java入門", "じろう"))
        val listView: ListView = findViewById(R.id.list_view)as ListView
        listView.adapter = listAdapter

//        articleView.setArticle(Article(id = "123",
//                title = "Kotlin入門",
//                url = "http://www.example.com/articles/123",
//                user = User(id = "456",name = "たろう", profileImageURI = "")))
//
//        setContentView(articleView)
    }

    private fun dummyArticle(title: String, userName: String): Article =
            Article(id = "",
                    url = "https://kotlinlang.org/",
                    user = User(id = "", name = userName, profileImageURI = ""))

}
