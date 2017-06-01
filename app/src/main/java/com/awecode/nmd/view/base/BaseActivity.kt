package com.awecode.nmd.view.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.awecode.nmd.R

/**
 * Created by munnadroid on 5/23/17.
 */

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

    abstract val layoutId: Int


    fun get_color(colorId: Int): Int {
        return ContextCompat.getColor(applicationContext, colorId)
    }

    fun changeFragment(fragment: Fragment, cleanStack: Boolean = false, addToBackStack: Boolean = true) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.replace(R.id.container, fragment)
        if (addToBackStack)
            ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    /**
     * Finish activity when reaching the last fragment.
     */
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1)
            fragmentManager.popBackStack()
        else
            finish()

    }
}
