/*
 * DrawerAdapter.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 11:26 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.materialDrawer

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kucingapes.ankomaterialsample.MaterialUtils
import com.kucingapes.ankomaterialsample.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.textColorResource

class DrawerAdapter(private var items: MutableList<ItemDrawer>,
                    private var listener: DrawerItemClick
) : RecyclerView.Adapter<DrawerAdapter.Holder>() {
    private lateinit var context: Context
    private var selectedItem = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(
            ItemDrawerUi().createView(
                AnkoContext.create(context, parent)
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]

        val containerItem: CardView = holder.itemView.find(R.id.drawer_card_container)
        val iconItem: ImageView = holder.itemView.find(R.id.drawer_item_icon)
        val textItem: TextView = holder.itemView.find(R.id.drawer_item_text)
        val divider: View = holder.itemView.find(R.id.drawer_divider)

        if (item.divider) {
            visibility(containerItem, GONE)
            visibility(iconItem, GONE)
            visibility(textItem, GONE)
            visibility(divider, VISIBLE)
        } else {
            visibility(containerItem, VISIBLE)
            visibility(iconItem, VISIBLE)
            visibility(textItem, VISIBLE)
            visibility(divider, GONE)
        }

        iconItem.setImageResource(item.icon)
        textItem.text = item.title
        containerItem.setOnClickListener {
            if (item.focus) {
                selectedItem = position
            }
            listener.onDrawerClick(position, item)
            notifyDataSetChanged()
        }

        when (selectedItem) {
            position -> {
                containerItem.setCardBackgroundColor(ContextCompat.getColor(context,
                    R.color.colorPrimary
                ))
                textItem.textColorResource = android.R.color.white
                MaterialUtils.imageTint(iconItem, android.R.color.white)
            }

            else -> {
                containerItem.setCardBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
                textItem.textColorResource = R.color.colorPrimary
                MaterialUtils.imageTint(
                    iconItem,
                    R.color.colorPrimary
                )
            }
        }
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    fun visibility(view: View, visibility: Int) {
        when (visibility) {
            GONE -> view.visibility = View.GONE
            VISIBLE -> view.visibility = View.VISIBLE
        }
    }

    companion object {
        const val GONE = 0
        const val VISIBLE = 1
    }
}