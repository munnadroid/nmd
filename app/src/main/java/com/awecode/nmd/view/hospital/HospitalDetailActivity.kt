package com.awecode.nmd.view.hospital

import android.graphics.Color
import android.os.Bundle
import com.awecode.nmd.R
import com.awecode.nmd.models.Hospital
import com.awecode.nmd.util.Util
import com.awecode.nmd.util.extensions.changeDefaultNavIconColor
import com.awecode.nmd.util.extensions.colorRes
import com.awecode.nmd.view.base.BaseActivity
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_hospital_detail.*
import kotlinx.android.synthetic.main.item_hospital.*
import org.jetbrains.anko.browse
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
        try {
            var hospital = intent.getParcelableExtra<Hospital>(INTENT_HOSPITAL_DATA)
            var lastLatLong = intent.getParcelableExtra<LatLng>(INTENT_MYLAST_LATLONG)
            if (hospital != null
                    && lastLatLong != null) {
                setupToolbar(hospital)
                populateDataInView(hospital, lastLatLong)
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    private fun populateDataInView(hospital: Hospital, lastLatLong: LatLng) {
        nameTextView.text = hospital.name
        addressTextView.text = hospital.address

        websiteTextView.text = hospital.website
        websiteTextView.setOnClickListener {
            browse(hospital.website)
        }

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
            changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))
        }


        collapsibleToolbarLayout.setExpandedTitleColor(Color.parseColor("#00ffffff"))
        collapsibleToolbarLayout.isTitleEnabled = false
        supportActionBar?.title = hospital.name

        collapsibleToolbarLayout.invalidate()
        toolbar.invalidate()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}