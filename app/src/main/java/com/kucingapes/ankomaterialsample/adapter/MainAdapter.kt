/*
 * MainAdapter.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 10:19 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kucingapes.ankomaterialsample.ankoUi.ItemMainUi
import com.kucingapes.ankomaterialsample.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MainAdapter(private var items: MutableList<String>) : RecyclerView.Adapter<MainAdapter.Holder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(
            ItemMainUi().createView(
                AnkoContext.create(context, parent)
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val url = items[position]
        val image = holder.itemView.find(R.id.cat_image) as ImageView

        Picasso.get().load(url).into(image)
        image.setOnClickListener {
            context.toast("item clicked at $position")
        }
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}