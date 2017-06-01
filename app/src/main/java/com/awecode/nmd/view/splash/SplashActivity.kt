package com.awecode.nmd.view.splash

import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import com.awecode.nmd.MainActivity
import com.awecode.nmd.R
import com.awecode.nmd.util.PrefsHelper
import com.awecode.nmd.view.login.LoginActivity
import com.awecode.nmd.util.extensions.launchActivity
import com.awecode.nmd.util.extensions.makeFullscreen
import com.awecode.nmd.view.base.BaseActivity


/**
 * Created by munnadroid on 5/30/17.
 */
class SplashActivity : BaseActivity() {
    override val layoutId = R.layout.activity_splash
    val SPLASH_DISPLAY_LENGTH: Long = 3 * 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.makeFullscreen()

        Handler().postDelayed({
            if (PrefsHelper.getLoginStatus())
                launchActivity<MainActivity> { }
            else
                launchActivity<LoginActivity> { }
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}