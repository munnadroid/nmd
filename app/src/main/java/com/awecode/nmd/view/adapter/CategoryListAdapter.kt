package com.awecode.nmd.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.awecode.nmd.R
import com.awecode.nmd.models.Specialists
import com.awecode.nmd.util.textdrawable.TextDrawable
import com.awecode.nmd.util.textdrawable.util.ColorGenerator
import kotlinx.android.synthetic.main.item_category.view.*


/**
 * Created by munnadroid on 5/26/17.
 */

class CategoryListAdapter(val specialList: List<Specialists>, val itemClick: (Specialists) -> Unit) :
        RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(specialList[position])
    }

    override fun getItemCount() = specialList.size


    class ViewHolder(view: View, val itemClick: (Specialists) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(data: Specialists) {
            with(data) {
                itemView.nameTextView.text = data.name

                val generator = ColorGenerator.MATERIAL // or use DEFAULT
                // generate random color
                val color = generator.randomColor
                val drawable = TextDrawable.builder()
                        .buildRound(data.totalCount.toString(), color)
                itemView.imageView.setImageDrawable(drawable)


                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}