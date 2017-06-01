package com.awecode.nmd.view.hospital

import android.os.Bundle
import com.awecode.nmd.R
import com.awecode.nmd.models.Hospital
import com.awecode.stockapp.util.Util
import com.awecode.stockapp.util.extensions.changeDefaultNavIconColor
import com.awecode.stockapp.util.extensions.colorRes
import com.awecode.stockapp.view.base.BaseActivity
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_hospital_detail.*
import kotlinx.android.synthetic.main.item_hospital.*
import org.jetbrains.anko.email
import org.jetbrains.anko.makeCall

/**
 * Created by munnadroid on 6/1/17.
 */
class HospitalDetailActivity : BaseActivity() {
    override val layoutId = R.layout.activity_hospital_detail


    companion object {
        val INTENT_HOSPITAL_DATA: String = "intent_hospital_data"
        val INTENT_MYLAST_LATLONG: String = "intent_my_last_latlng"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var hospital = intent.getParcelableExtra<Hospital>(INTENT_HOSPITAL_DATA)
        var lastLatLong = intent.getParcelableExtra<LatLng>(INTENT_MYLAST_LATLONG)
        if (hospital != null
                && lastLatLong != null) {
            setupToolbar(hospital)
            populateDataInView(hospital, lastLatLong)
        }
    }

    private fun populateDataInView(hospital: Hospital, lastLatLong: LatLng) {
        nameTextView.text = hospital.name
        addressTextView.text = hospital.address

        callLayout.setOnClickListener {
            makeCall(hospital.telephone)
        }

        directionLayout.setOnClickListener {
            Util.showDirection(LatLng(lastLatLong.latitude, lastLatLong.longitude),
                    LatLng(hospital.latitude, hospital.longitude),
                    applicationContext)
        }
        emailLayout.setOnClickListener {
            email(hospital.email)
        }

    }

    private fun setupToolbar(hospital: Hospital) {
        setSupportActionBar(toolbar)
        with(supportActionBar!!) {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        supportActionBar?.changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))
        supportActionBar?.title = hospital.name
    }

}