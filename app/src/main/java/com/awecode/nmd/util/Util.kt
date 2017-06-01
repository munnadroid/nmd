package com.awecode.stockapp.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.Toolbar
import com.google.android.gms.maps.model.LatLng

/**
 * Created by munnadroid on 5/23/17.
 */

class Util {
    companion object {
        fun setToolbarTitle(toolbar: Toolbar, title: String) {
            toolbar.title = title
        }

        fun showDirection(sourceLatLng: LatLng, destLatLng: LatLng, ctx: Context) {
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?f=d&saddr=${sourceLatLng.latitude},${sourceLatLng.longitude}" +
                            "&daddr=${destLatLng.latitude},${destLatLng.longitude}"))
            intent.component = ComponentName("com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity")
            ctx.startActivity(intent)
        }
    }


}


