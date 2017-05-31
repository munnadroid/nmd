package com.awecode.nmd.view.doctor.list

import android.os.Bundle
import com.awecode.nmd.R
import com.awecode.nmd.models.Specialists
import com.awecode.stockapp.util.extensions.changeDefaultNavIconColor
import com.awecode.stockapp.util.extensions.colorRes
import com.awecode.stockapp.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*

/**
 * Created by munnadroid on 5/30/17.
 */
class DoctorListActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_category

    companion object {
        val INTENT_DATA: String = "intent_specialist_data"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var specialist = intent.getParcelableExtra<Specialists>(INTENT_DATA)
        requireNotNull(specialist) { finish() }

        setupToolbar(specialist)

        changeFragment(DoctorListFragment.newInstance(), addToBackStack = false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setupToolbar(specialist: Specialists) {
        setSupportActionBar(toolbar)

        supportActionBar?.title = specialist.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))

    }

}