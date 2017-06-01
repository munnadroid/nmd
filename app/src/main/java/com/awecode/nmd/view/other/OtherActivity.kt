package com.awecode.nmd.view.other

import android.os.Bundle
import com.awecode.nmd.R
import com.awecode.stockapp.util.extensions.changeDefaultNavIconColor
import com.awecode.stockapp.util.extensions.colorRes
import com.awecode.stockapp.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_other.*

/**
 * Created by munnadroid on 6/1/17.
 */
class OtherActivity : BaseActivity() {
    override val layoutId: Int = R.layout.activity_other

    companion object {
        val INTENT_VIEW_TYPE = "intent_other_view_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewType = intent.getSerializableExtra(INTENT_VIEW_TYPE) as VIEWTYPE
        setupToolbar(viewType)
        if(viewType==VIEWTYPE.ABOUTUS)
            changeFragment(AboutUsFragment.newInstance())

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setupToolbar(viewType: VIEWTYPE) {
        setSupportActionBar(toolbar)

        with(supportActionBar!!) {
            if (viewType == VIEWTYPE.ABOUTUS)
                title = getString(R.string.aboutus)
            else if (viewType == VIEWTYPE.CONTACTUS)
                title = getString(R.string.contactus)

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            changeDefaultNavIconColor(applicationContext, colorRes(R.color.white))
        }


    }

    enum class VIEWTYPE {
        ABOUTUS,
        CONTACTUS

    }

}