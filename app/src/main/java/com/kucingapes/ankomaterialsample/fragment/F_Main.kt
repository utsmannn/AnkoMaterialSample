/*
 * F_Main.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/5/18 12:22 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kucingapes.ankomaterialsample.R
import com.kucingapes.ankomaterialsample.adapter.MainAdapter
import com.kucingapes.ankomaterialsample.BaseUi.baseRecyclerView
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class F_Main : Fragment(), AnkoComponent<Context>, AnkoLogger {

    private val items: MutableList<String> = mutableListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        coordinatorLayout {
            baseRecyclerView(items).apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = MainAdapter(items)
            }
        }
    }
}