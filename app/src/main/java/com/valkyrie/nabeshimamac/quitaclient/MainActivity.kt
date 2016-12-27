package com.valkyrie.nabeshimamac.quitaclient

import android.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        articleView.setArticle(Article(id = "123",
                title = "Kotlin入門",
                url = "http://www.example.com/articles/123",
                user = User(id = "456",name = "たろう", profileImageURI = "")))

        setContentView(articleView)
    }
}
