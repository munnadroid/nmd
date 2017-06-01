package com.awecode.stockapp.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.awecode.nmd.R
import com.awecode.nmd.listener.ButtonType
import com.awecode.nmd.listener.HospitalBtnClickListener
import com.awecode.nmd.models.Hospital
import kotlinx.android.synthetic.main.item_hospital.view.*


/**
 * Created by munnadroid on 5/26/17.
 */

class HospitalListAdapter(val dataList: List<Hospital>, val itemClick: (Hospital) -> Unit) :
        RecyclerView.Adapter<HospitalListAdapter.ViewHolder>() {
    public var hospitalBtnClickListener: HospitalBtnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(dataList[position], hospitalBtnClickListener)
    }

    override fun getItemCount() = dataList.size


    class ViewHolder(view: View, val itemClick: (Hospital) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(data: Hospital, hospitalBtnClickListener: HospitalBtnClickListener?) {
            with(data) {
                itemView.nameTextView.text = data.name
                itemView.addressTextView.text = data.address

                itemView.callLayout.setOnClickListener {
                    hospitalBtnClickListener?.onBtnClickListener(ButtonType.CALL,data)
                }

                itemView.directionLayout.setOnClickListener {
                    hospitalBtnClickListener?.onBtnClickListener(ButtonType.DIRECTION,data)
                }
                itemView.emailLayout.setOnClickListener {
                    hospitalBtnClickListener?.onBtnClickListener(ButtonType.EMAIL,data)
                }


                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}