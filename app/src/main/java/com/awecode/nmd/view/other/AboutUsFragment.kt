package com.awecode.nmd.view.other

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.awecode.nmd.R
import com.awecode.stockapp.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_aboutus.*

/**
 * Created by munnadroid on 6/1/17.
 */
class AboutUsFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_aboutus

    companion object {
        fun newInstance(): AboutUsFragment {
            return AboutUsFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        telephoneTextView.setOnClickListener {

            var callIntent: Intent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:977-1-4567890")
            startActivity(callIntent)
        }

        emailTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:") // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "info@nmd.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            if (intent.resolveActivity(activity.packageManager) != null) {
                startActivity(intent)
            }
        }

        websiteTextView.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("https://nepalmedicaldirectory.com")
            startActivity(i)
        }
    }
}