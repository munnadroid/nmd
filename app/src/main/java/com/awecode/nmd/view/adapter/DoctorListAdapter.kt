package com.awecode.nmd.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.awecode.nmd.R
import com.awecode.nmd.models.Doctor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_doctorlist.view.*


/**
 * Created by munnadroid on 5/26/17.
 */

class DoctorListAdapter(val dataList: List<Doctor>, val itemClick: (Doctor) -> Unit) :
        RecyclerView.Adapter<DoctorListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctorlist, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(dataList[position])
    }

    override fun getItemCount() = dataList.size


    class ViewHolder(view: View, val itemClick: (Doctor) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(data: Doctor) {
            with(data) {
                itemView.nameTextView.text = data.name
                itemView.designationTextView.text = data.designation + "\n" + data.hospitalName
                itemView.experienceTextView.text = "Experience " + data.experience

                Picasso.with(itemView.context)
                        .load(data.profilePic)
                        .placeholder(R.drawable.person_placeholder)
                        .into(itemView.profileImageView)

                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}