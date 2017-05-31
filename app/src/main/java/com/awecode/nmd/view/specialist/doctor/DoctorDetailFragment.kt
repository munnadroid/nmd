package com.awecode.nmd.view.specialist.doctor

import android.os.Bundle
import android.view.View
import com.awecode.nmd.R
import com.awecode.nmd.models.Doctor
import com.awecode.stockapp.util.extensions.toast
import com.awecode.stockapp.view.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_doctor_detail.*
import kotlinx.android.synthetic.main.item_doctorlist.*

/**
 * Created by munnadroid on 5/31/17.
 */
class DoctorDetailFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_doctor_detail

    companion object {
        fun newInstance(doctor: Doctor): DoctorDetailFragment {
            var fragment = DoctorDetailFragment()
            fragment.setData(doctor)
            return fragment
        }
    }

    private var mDoctor: Doctor? = null

    private fun setData(doctor: Doctor) {
        this.mDoctor = doctor
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            nameTextView.text = mDoctor?.name
            designationTextView.text = mDoctor?.designation + "\n" + mDoctor?.hospitalName
            experienceTextView.text = "Experience " + mDoctor?.experience

            Picasso.with(context)
                    .load(mDoctor?.profilePic)
                    .placeholder(R.drawable.person_placeholder)
                    .into(profileImageView)

        bookNowButton.setOnClickListener { toast(getString(R.string.feature_available_soon)) }

    }
}