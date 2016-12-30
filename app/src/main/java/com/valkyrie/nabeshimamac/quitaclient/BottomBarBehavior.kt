package com.valkyrie.nabeshimamac.quitaclient

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout


/**
 * Created by NabeshimaMAC on 2016/12/29.
 */
class BottomBarBehavior(context: Context?, attrs: AttributeSet?) :
        CoordinatorLayout.Behavior<LinearLayout>(context, attrs) {

    private var defaultDependencyTop: Float = -1f

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: LinearLayout?, dependency: View?): Boolean =
            dependency is AppBarLayout

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: LinearLayout?, dependency: View?): Boolean =
            dependency?.let {
                if (defaultDependencyTop == -1f) {
                    defaultDependencyTop = it.top.toFloat()
                }
                ViewCompat.setTranslationY(child, -it.top + defaultDependencyTop)
                true
            } ?: let {
                false
            }
}