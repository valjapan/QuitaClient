package com.valkyrie.nabeshimamac.quitaclient

import android.content.Context
import android.graphics.Color
import android.support.annotation.IdRes
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class ArticleView : FrameLayout {
    constructor(context: Context?) : super(context)

    constructor(context: Context?,
                attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
//
//    var profileImageView: ImageView? = null
//
//    var titleTextView: TextView? = null
//
//    var userNameTextView: TextView? = null

    fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
        findViewById(id) as T
    }

    val profileImageView: ImageView by bindView(R.id.profile_image_view)

    val titleTextView: TextView by bindView(R.id.title_text_view)

    val userNameTextView: TextView by bindView(R.id.user_name_text_view)

//    init {
//        LayoutInflater.from(context).inflate(R.layout.content_article_view, this)
//        profileImageView = findViewById(R.id.profile_image_view) as ImageView
//        titleTextView = findViewById(R.id.title_text_view) as TextView
//        userNameTextView = findViewById(R.id.user_name_text_view) as TextView
//    }

    fun setArticle(article: Article){
        titleTextView?.text = article.title
        userNameTextView?.text = article.user.name

        profileImageView?.setBackgroundColor(Color.RED)
    }

}
