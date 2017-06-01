package com.awecode.nmd.view.hospital

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.awecode.nmd.R
import com.awecode.nmd.listener.ButtonType
import com.awecode.nmd.listener.HospitalBtnClickListener
import com.awecode.nmd.models.Hospital
import com.awecode.stockapp.util.Util
import com.awecode.stockapp.util.extensions.changeDefaultNavIconColor
import com.awecode.stockapp.util.extensions.colorRes
import com.awecode.stockapp.util.extensions.launchActivity
import com.awecode.stockapp.util.extensions.toast
import com.awecode.stockapp.view.adapter.HospitalListAdapter
import com.awecode.stockapp.view.base.BaseActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices.API
import com.google.android.gms.location.LocationServices.FusedLocationApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_hospital.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.email
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.uiThread


class HospitalActivity : BaseActivity(),
        OnMapReadyCallback,
        HospitalBtnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    private var mMap: GoogleMap? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private var hospitalList: List<Hospital>? = null
    private var mLastLocation: Location? = null
    private var mLastLatLong: LatLng? = null

    override val layoutId: Int = R.layout.activity_hospital

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        hospitalList = getDummyList()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupListAdapter()

        checkLocationCallPermission()
    }


    /**
     * request api and populate in list in view
     */
    fun setupListAdapter() = doAsync {
        var dataList = getDummyList()
        uiThread {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            val adapter = HospitalListAdapter(dataList) {
                goToDetailView(it)
            }
            adapter.hospitalBtnClickListener = this@HospitalActivity
            recyclerView.adapter = adapter
        }

    }

    private fun goToDetailView(hospital: Hospital) {
        launchActivity<HospitalDetailActivity> {
            putExtra(HospitalDetailActivity.INTENT_HOSPITAL_DATA, hospital)
            putExtra(HospitalDetailActivity.INTENT_MYLAST_LATLONG, mLastLatLong)
        }
    }

    override fun onBtnClickListener(buttonType: ButtonType, hospital: Hospital) {
        when (buttonType) {
            ButtonType.CALL -> makeCall(hospital.telephone)
            ButtonType.DIRECTION -> {
                if (mLastLatLong != null)
                    Util.showDirection(LatLng(mLastLatLong!!.latitude, mLastLatLong!!.longitude),
                            LatLng(hospital.latitude, hospital.longitude),
                            applicationContext)
            }
            ButtonType.EMAIL -> email(hospital.email)
        }
    }

    private fun checkLocationCallPermission() {
        val rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CALL_PHONE)
                .subscribe { granted ->
                    if (granted) {
                        // All requested permissions are granted
                        initializeGoogleApiClient()

                    } else {
                        // At least one permission is denied
                    }
                }
    }


    private fun initializeGoogleApiClient() {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null)
            mGoogleApiClient = GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(API)
                    .build()

    }


    @SuppressLint("MissingPermission")
    override fun onConnected(bundle: Bundle?) {
        mLastLocation = FusedLocationApi.getLastLocation(
                mGoogleApiClient)
        if (mLastLocation != null)
            mLastLatLong = LatLng(mLastLocation?.latitude as Double, mLastLocation?.longitude as Double)

    }


    override fun onConnectionSuspended(i: Int) {

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        toast("Problem occured while getting your location.")
    }

    override fun onStart() {
        mGoogleApiClient?.connect()
        super.onStart()
    }

    override fun onStop() {
        mGoogleApiClient?.disconnect()
        super.onStop()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setupToolbar() {
        setSupportActionBar(toolbar)

        with(supportActionBar!!) {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            supportActionBar?.changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))
        }


        collapsibleToolbarLayout.setExpandedTitleColor(Color.parseColor("#00ffffff"))
        collapsibleToolbarLayout.isTitleEnabled = false
        supportActionBar?.title = "Hospitals"

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
        mMap!!.setOnInfoWindowClickListener { marker ->
            val title = marker.title
            hospitalList!!
                    .filter { it.name == title }
                    .forEach { goToDetailView(it) }
        }

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
            add(Hospital("Grande International Hospital", "grande@gmail.com", "Tokha, Kathmandu", "01523383",
                    27.663002, 85.277421, "https://grande.com.np"))
            add(Hospital("Nobel Medical Hospital", "nobel@gmail.com", "Sinamangal, Kathmandu", "01433381",
                    27.666344, 85.272521, "https://nobelhospital.com.np"))
            add(Hospital("Bir Hospital", "bir@gmail.com", "Sundhara, Kathmandu", "01500380",
                    27.664102, 85.286769, "https://birhospital.com.np"))
            add(Hospital("Lumbini Medical College", "lumbini@lumbini.com.np", "Kirtipur, Kathmandu", "01500111",
                    27.656366, 85.277950, "https://lumbinihospital.com.np"))
            add(Hospital("Star Hospital", "start@star.com.np", "Chabahil, Kathmandu", "01544385",
                    27.653573, 85.286855, "https://starthospital.com.np"))
            add(Hospital("Kist Hospital", "kist@gmail.com", "Gwarko, Lalitpur", "01428899",
                    27.649391, 85.279194, "https://kist.com.np"))
            add(Hospital("Global Hospital", "global@gmail.com", "Balkumari, Lalitpur", "01577787",
                    27.667161, 85.285116, "https://globalhospital.com.np"))
        }
    }


}
