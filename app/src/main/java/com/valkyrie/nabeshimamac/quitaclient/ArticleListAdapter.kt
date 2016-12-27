package com.valkyrie.nabeshimamac.quitaclient

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.text.FieldPosition

/**
 * Created by NabeshimaMAC on 2016/12/25.
 */
class ArticleListAdapter(private val context: Context) : BaseAdapter() {

    var articles: List<Article> = enptyList()

    override fun getCount(): Int = articles.size

    override fun getItem(position: Int): Any? = articles[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int,
                         contextView: View?,
                         parent: ViewGroup?): View =
            ((contextView as? ArticleView) ?: ArticleView(context)).apply {
                setArticle(articles[position])
            }

    public inline fun <T> T.apply(block: T.() -> Unit): T {block(); return this}
}