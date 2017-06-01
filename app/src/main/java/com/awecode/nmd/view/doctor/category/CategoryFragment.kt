package com.awecode.nmd.view.doctor.category

import com.awecode.nmd.view.doctor.list.DoctorListActivity
import com.awecode.nmd.view.doctor.list.DoctorListFragment
import com.awecode.stockapp.util.extensions.hide
import com.awecode.stockapp.util.extensions.launchActivity
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by munnadroid on 5/30/17.
 */
class CategoryFragment : com.awecode.stockapp.view.base.BaseFragment() {
    override val layoutId: Int = com.awecode.nmd.R.layout.fragment_category

    companion object {
        fun newInstance(): com.awecode.nmd.view.doctor.category.CategoryFragment {
            return com.awecode.nmd.view.doctor.category.CategoryFragment()
        }

        /**
         * for side menu - opened using activity- categoryactivity
         */
        fun newInstance(isFromActivity: Boolean): com.awecode.nmd.view.doctor.category.CategoryFragment {
            var fragment = com.awecode.nmd.view.doctor.category.CategoryFragment()
            fragment.setData(isFromActivity)
            return fragment
        }
    }

    private var mIsFromActivity: Boolean = false

    private fun setData(fromActivity: Boolean) {
        this.mIsFromActivity = fromActivity;
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mIsFromActivity)
            titleTextView.hide()
        setupListAdapter()
    }

    /**
     * request api and populate in list in view
     */
    fun setupListAdapter() = doAsync {
        try {
            var dataList = getDummyList()
            uiThread {
                recyclerView?.layoutManager = android.support.v7.widget.LinearLayoutManager(activity)
                val adapter = com.awecode.stockapp.view.adapter.CategoryListAdapter(dataList) {
                    if (mIsFromActivity)
                        changeFragment(DoctorListFragment.newInstance())
                    else
                        launchActivity<DoctorListActivity> {
                            putExtra(DoctorListActivity.INTENT_DATA, it)
                        }
                }
                recyclerView?.adapter = adapter
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }

    }


    fun getDummyList(): List<com.awecode.nmd.models.Specialists> {
        return ArrayList<com.awecode.nmd.models.Specialists>().apply {
            add(com.awecode.nmd.models.Specialists("Allergist", 1, 10))
            add(com.awecode.nmd.models.Specialists("Anesthesiologist", 2, 20))
            add(com.awecode.nmd.models.Specialists("Cardiologist", 3, 5))
            add(com.awecode.nmd.models.Specialists("Dermatologist", 1, 20))
            add(com.awecode.nmd.models.Specialists("Gastroenterologist", 1, 12))
            add(com.awecode.nmd.models.Specialists("Cardiovascular surgeon", 1, 8))
            add(com.awecode.nmd.models.Specialists("Endocrinologist", 1, 7))
            add(com.awecode.nmd.models.Specialists("Forensic pathologist", 1, 23))
            add(com.awecode.nmd.models.Specialists("Gynecologist", 1, 14))
            add(com.awecode.nmd.models.Specialists("Neurologist", 1, 12))
            add(com.awecode.nmd.models.Specialists("Oncologist", 1, 9))
            add(com.awecode.nmd.models.Specialists("Ophthalmologist", 1, 21))

            add(com.awecode.nmd.models.Specialists("Allergist", 1, 10))
            add(com.awecode.nmd.models.Specialists("Anesthesiologist", 2, 20))
            add(com.awecode.nmd.models.Specialists("Cardiologist", 3, 5))
            add(com.awecode.nmd.models.Specialists("Dermatologist", 1, 20))
            add(com.awecode.nmd.models.Specialists("Gastroenterologist", 1, 12))
            add(com.awecode.nmd.models.Specialists("Cardiovascular surgeon", 1, 8))
            add(com.awecode.nmd.models.Specialists("Endocrinologist", 1, 7))
            add(com.awecode.nmd.models.Specialists("Forensic pathologist", 1, 23))
            add(com.awecode.nmd.models.Specialists("Gynecologist", 1, 14))
            add(com.awecode.nmd.models.Specialists("Neurologist", 1, 12))
            add(com.awecode.nmd.models.Specialists("Oncologist", 1, 9))
            add(com.awecode.nmd.models.Specialists("Ophthalmologist", 1, 21))
        }

    }
}