package com.valkyrie.nabeshimamac.quitaclient

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by NabeshimaMAC on 2016/12/29.
 */
class ArticleAdapter(private val context: Context, var articles: List<Article> = emptyList()) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    var listener: OnItemClickListener? = null

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(ArticleView(context))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let {
            it.articleView?.setArticle(articles[position])
            it.itemView.setOnClickListener {
                listener?.onItemClick(articles[position])
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleView: ArticleView = view as ArticleView
    }

    interface OnItemClickListener {
        fun onItemClick(article: Article)
    }

}