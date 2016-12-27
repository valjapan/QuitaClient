package com.valkyrie.nabeshimamac.quitaclient

import android.support.annotation.IdRes
import android.view.View

/**
 * Created by NabeshimaMAC on 2016/12/25.
 */

fun <T : View>View.bindView(@IdRes id : Int): Lazy<T> = lazy{
    findViewById(id) as T

}