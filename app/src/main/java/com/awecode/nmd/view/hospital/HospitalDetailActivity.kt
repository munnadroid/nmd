package com.awecode.nmd.view.hospital

import android.os.Bundle
import com.awecode.nmd.R
import com.awecode.nmd.models.Hospital
import com.awecode.stockapp.util.extensions.changeDefaultNavIconColor
import com.awecode.stockapp.util.extensions.colorRes
import com.awecode.stockapp.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_hospital_detail.*
import kotlinx.android.synthetic.main.item_hospital.*
import org.jetbrains.anko.email
import org.jetbrains.anko.makeCall

/**
 * Created by munnadroid on 6/1/17.
 */
class HospitalDetailActivity : BaseActivity() {
    override val layoutId = R.layout.activity_hospital_detail
    private var mHospital: Hospital? = null

    companion object {
        val INTENT_HOSPITAL_DATA: String = "intent_hospital_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHospital = intent.getParcelableExtra(INTENT_HOSPITAL_DATA)
        if (mHospital != null) {
            setupToolbar()
            populateDataInView(mHospital!!)
        }
    }

    private fun populateDataInView(hospital: Hospital) {
        nameTextView.text = hospital.name
        addressTextView.text = hospital.address

        callLayout.setOnClickListener {
            makeCall(hospital.telephone)
        }

        directionLayout.setOnClickListener {

        }
        emailLayout.setOnClickListener {
            email(hospital.email)
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))
        supportActionBar?.title = mHospital?.name
    }

}