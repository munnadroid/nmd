package com.awecode.nmd.util.extensions

import android.view.View

/**
 * Created by munnadroid on 5/29/17.
 */

fun View.show(): Unit {
    visibility = View.VISIBLE
}

fun View.hide(): Unit {
    visibility = View.GONE
}

fun View.toggleVisibility(): Unit = if (visibility == View.VISIBLE) hide() else show()