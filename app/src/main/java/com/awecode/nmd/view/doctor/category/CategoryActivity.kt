package com.awecode.nmd.view.doctor.category

import android.os.Bundle
import com.awecode.nmd.R
import com.awecode.nmd.util.extensions.changeDefaultNavIconColor
import com.awecode.nmd.util.extensions.colorRes
import com.awecode.nmd.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*

/**
 * Created by munnadroid on 5/30/17.
 */
class CategoryActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        changeFragment(CategoryFragment.Companion.newInstance(true), addToBackStack = false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setupToolbar() {
        setSupportActionBar(toolbar)

        with(supportActionBar!!) {
            title = getString(R.string.doctors)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))
        }


    }

}