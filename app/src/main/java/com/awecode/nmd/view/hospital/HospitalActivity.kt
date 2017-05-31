package com.awecode.nmd.view.hospital

import android.os.Bundle
import com.awecode.nmd.R
import com.awecode.nmd.models.Hospital
import com.awecode.stockapp.util.extensions.changeDefaultNavIconColor
import com.awecode.stockapp.util.extensions.colorRes
import com.awecode.stockapp.view.base.BaseActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_category.*

class HospitalActivity : BaseActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private var hospitalList: List<Hospital>? = null

    override val layoutId: Int = R.layout.activity_hospital

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()
        hospitalList = getDummyList()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setupToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Hospitals"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(27.667769, 85.277048), 14.0f))


        for (data in hospitalList!!) {
            // Add a marker in Sydney and move the camera
            val latlng = LatLng(data.latitude, data.longitude)
            with(mMap!!) {
                addMarker(MarkerOptions().position(latlng).title(data.name))
            }
        }
    }


    private fun getDummyList(): List<Hospital> {
        return ArrayList<Hospital>().apply {
            add(Hospital("Grande International Hospital", "Tokha, Kathmandu", "01523383",
                    27.663002, 85.277421))
            add(Hospital("Nobel Medical Hospital", "Sinamangal, Kathmandu", "01433381",
                    27.666344, 85.272521))
            add(Hospital("Bir Hospital", "Sundhara, Kathmandu", "01500380",
                    27.664102, 85.286769))
            add(Hospital("Lumbini Medical College", "Kirtipur, Kathmandu", "01500111",
                    27.656366, 85.277950))
            add(Hospital("Star Hospital", "Chabahil, Kathmandu", "01544385",
                    27.653573, 85.286855))
            add(Hospital("Kist Hospital", "Gwarko, Lalitpur", "01428899",
                    27.649391, 85.279194))
            add(Hospital("Global Hospital", "Balkumari, Lalitpur", "01577787",
                    27.667161, 85.285116))
        }
    }
}
