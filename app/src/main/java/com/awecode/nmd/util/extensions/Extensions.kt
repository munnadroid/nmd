package com.awecode.nmd.util.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.PorterDuff
import android.os.Build
import android.support.annotation.StyleRes
import android.support.design.widget.TabLayout
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.ActionBar
import android.text.Html
import android.view.View
import android.view.Window
import android.widget.TextView
import com.awecode.nmd.R


/**
 * Created by munnadroid on 5/24/17.
 */


fun Window.makeFullscreen() {
    // Note that some of these constants are new as of API 16 (Jelly Bean)
    // and API 19 (KitKat). It is safe to use them, as they are inlined
    // at compile-time and do nothing on earlier devices.
    val visibilityFlag = View.SYSTEM_UI_FLAG_LOW_PROFILE or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    this.decorView.systemUiVisibility = visibilityFlag
    this.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
        if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
            this.decorView.systemUiVisibility = visibilityFlag
        }
    }
}

fun ActionBar.changeDefaultNavIconColor(ctx: Context, color: Int) {
    val upArrow = ctx.drawableRes(R.drawable.abc_ic_ab_back_material)
    upArrow.setColorFilter(2, PorterDuff.Mode.SRC_ATOP)
    setHomeAsUpIndicator(upArrow)
}

fun TabLayout.changeTabIconColor(theme: Resources.Theme, slidingTabs: TabLayout, color: Int): Unit {
    val colors: ColorStateList
    if (Build.VERSION.SDK_INT >= 23) {
        colors = resources.getColorStateList(color, theme)
    } else {
        colors = resources.getColorStateList(color)
    }

    for (i in 0..slidingTabs.tabCount - 1) {
        val tab = slidingTabs.getTabAt(i)
        var icon = tab?.icon

        if (icon != null) {
            icon = DrawableCompat.wrap(icon!!)
            DrawableCompat.setTintList(icon!!, colors)
        }
    }


    fun TextView.setTextAppearanceC(@StyleRes textAppearance: Int)
            = TextViewCompat.setTextAppearance(this, textAppearance)

    @Suppress("DEPRECATION")
    fun String.fromHtml() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT);
    } else {
        Html.fromHtml(this)
    }


}
