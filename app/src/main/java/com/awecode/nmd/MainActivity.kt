package com.awecode.nmd

import android.os.Bundle
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import com.awecode.stockapp.view.base.BaseActivity
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override val layoutId = R.layout.activity_main
    private lateinit var result: Drawer
    private lateinit var headerResult: AccountHeader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        setupNavigationMenus(savedInstanceState)

    }

    fun setupNavigationMenus(savedInstanceState: Bundle?) {
        result = drawer {
            toolbar = this@MainActivity.toolbar
            hasStableIds = true
            savedInstance = savedInstanceState
            showOnFirstLaunch = true

            headerResult = accountHeader {
                background = R.color.colorPrimary
                savedInstance = savedInstanceState
                translucentStatusBar = true

                profile("Meg Ryan", "meg@gmail.com") {
                    iconUrl = "http://2.bp.blogspot.com/-oO8dYDnh8JY/TrUrINcULQI/AAAAAAAAFsk/3nAkhc0sdUY/s1600/1.jpg"
                    identifier = 100
                }
                onProfileChanged { _, profile, _ ->
                    if (profile.identifier == 100_000L) {
                        val size = headerResult.profiles.size
                        val newProfile = ProfileDrawerItem()
                                .withName("New Batman ${size - 1}")
                                .withNameShown(true)
                                .withEmail("batman${size - 1}@gmail.com")
                                .withIcon(R.mipmap.ic_launcher)
                                .withIdentifier(100L + size + 1L)
                        headerResult.addProfile(newProfile, size - 2)
                    }
                    false
                }
            }

            primaryItem("Home") {
                iicon = GoogleMaterial.Icon.gmd_home
            }

            primaryItem("Doctors") {
                iicon = GoogleMaterial.Icon.gmd_person
            }

            primaryItem("Hospitals") {
                iicon = GoogleMaterial.Icon.gmd_list
            }


            divider {}

            primaryItem("Setting") {
                iicon = GoogleMaterial.Icon.gmd_settings
            }
            primaryItem("Support") {
                iicon = GoogleMaterial.Icon.gmd_help
            }
            primaryItem("Logout") {
                iicon = FontAwesome.Icon.faw_sign_out
            }


        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        result.saveInstanceState(outState)
        headerResult.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (result.isDrawerOpen)
            result.closeDrawer()
        else
            super.onBackPressed()
    }
}
