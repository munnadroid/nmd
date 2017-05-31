package com.awecode.nmd.view.doctor.list

import android.os.Bundle
import com.awecode.nmd.R
import com.awecode.nmd.models.Doctor
import com.awecode.nmd.view.doctor.detail.DoctorDetailFragment
import com.awecode.stockapp.util.extensions.changeDefaultNavIconColor
import com.awecode.stockapp.util.extensions.colorRes
import com.awecode.stockapp.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_category.*

/**
 * Created by munnadroid on 5/30/17.
 */
class DoctorDetailActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_category

    companion object {
        val INTENT_DATA: String = "intent_doctor_data"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var doctor = intent.getParcelableExtra<Doctor>(INTENT_DATA)
        requireNotNull(doctor) { finish() }

        setupToolbar(doctor)

        changeFragment(DoctorDetailFragment.newInstance(doctor))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setupToolbar(doctor: Doctor) {
        setSupportActionBar(toolbar)

        supportActionBar?.title = doctor.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))

    }

}