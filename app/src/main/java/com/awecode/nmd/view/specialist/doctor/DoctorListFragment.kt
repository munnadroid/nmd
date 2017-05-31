package com.awecode.nmd.view.specialist.doctor

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.awecode.nmd.R
import com.awecode.nmd.models.Doctor
import com.awecode.nmd.view.specialist.CategoryFragment
import com.awecode.stockapp.util.extensions.launchActivity
import com.awecode.stockapp.util.extensions.v
import com.awecode.stockapp.view.adapter.DoctorListAdapter
import com.awecode.stockapp.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_doctor_list.*

/**
 * Created by munnadroid on 5/30/17.
 */
class DoctorListFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_doctor_list

    companion object {
        fun newInstance(): DoctorListFragment {
            return DoctorListFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListAdapter()
    }

    /**
     * request api and populate in list in view
     */
    fun setupListAdapter() {
        var dataList = getDummyList()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = DoctorListAdapter(dataList) {
           launchActivity<DoctorDetailActivity> {
               putExtra(DoctorDetailActivity.INTENT_DATA,it)
           }
        }
        recyclerView.adapter = adapter

    }

    fun getDummyList(): List<Doctor> {
        return ArrayList<Doctor>().apply {

            add(Doctor("Dr. Akhilesh Kumar Kasyap", "Grande City Hospital", "akhil@gmail.com",
                    "Consultant Gastroenterologist", "MD", "4 years", doctor_1_image,
                    "Gastroenterology & Hepatology", "Samakhusi, Kathmandu"))
            add(Doctor("Dr. Anju Dongol", "Grande City Hospital", "anju@gmail.com",
                    "Consultant, Pediatrics", "MD", "5 years", doctor_2_image,
                    "Pediatrics & Neonatology", "Imadole, Lalitpur"))

            add(Doctor("Dr. Akhilesh Kumar Kasyap", "Grande City Hospital", "akhil@gmail.com",
                    "Consultant Gastroenterologist", "MD", "4 years", doctor_1_image,
                    "Gastroenterology & Hepatology", "Samakhusi, Kathmandu"))
            add(Doctor("Dr. Anju Dongol", "Grande City Hospital", "anju@gmail.com",
                    "Consultant, Pediatrics", "MD", "5 years", doctor_2_image,
                    "Pediatrics & Neonatology", "Imadole, Lalitpur"))
            add(Doctor("Dr. Akhilesh Kumar Kasyap", "Grande City Hospital", "akhil@gmail.com",
                    "Consultant Gastroenterologist", "MD", "4 years", doctor_1_image,
                    "Gastroenterology & Hepatology", "Samakhusi, Kathmandu"))
            add(Doctor("Dr. Anju Dongol", "Grande City Hospital", "anju@gmail.com",
                    "Consultant, Pediatrics", "MD", "5 years", doctor_2_image,
                    "Pediatrics & Neonatology", "Imadole, Lalitpur"))
            add(Doctor("Dr. Akhilesh Kumar Kasyap", "Grande City Hospital", "akhil@gmail.com",
                    "Consultant Gastroenterologist", "MD", "4 years", doctor_1_image,
                    "Gastroenterology & Hepatology", "Samakhusi, Kathmandu"))
            add(Doctor("Dr. Anju Dongol", "Grande City Hospital", "anju@gmail.com",
                    "Consultant, Pediatrics", "MD", "5 years", doctor_2_image,
                    "Pediatrics & Neonatology", "Imadole, Lalitpur"))
            add(Doctor("Dr. Akhilesh Kumar Kasyap", "Grande City Hospital", "akhil@gmail.com",
                    "Consultant Gastroenterologist", "MD", "4 years", doctor_1_image,
                    "Gastroenterology & Hepatology", "Samakhusi, Kathmandu"))
            add(Doctor("Dr. Anju Dongol", "Grande City Hospital", "anju@gmail.com",
                    "Consultant, Pediatrics", "MD", "5 years", doctor_2_image,
                    "Pediatrics & Neonatology", "Imadole, Lalitpur"))
        }
    }

    val doctor_1_image: String = "http://grandehospital.com/uploads/doctors/image/56f366e493a3d-akhilesh-kumar-kasyap.JPG"
    val doctor_2_image: String = "http://grandehospital.com/uploads/doctors/image/58a306abd1d0b-anju-dangol.JPG"

}