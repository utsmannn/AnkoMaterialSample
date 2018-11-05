/*
 * F_Ripple.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/5/18 1:05 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kucingapes.ankomaterialsample.MaterialUtils
import com.kucingapes.ankomaterialsample.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class F_Ripple : Fragment(), AnkoComponent<Context> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        verticalLayout {
            cardView {
                textView("card ripple") {
                    MaterialUtils.materialText(this)
                }.lparams(wrapContent, wrapContent) {
                    gravity = Gravity.CENTER
                }


                MaterialUtils.materialCard(this)
                MaterialUtils.rippleThis(
                    this,
                    MaterialUtils.FOREGROUND
                )

                setOnClickListener {
                    context.toast("ripple")
                }
            }.lparams(matchParent, dip(250)) {
                margin = dip(12)
            }

            relativeLayout {
                textView("layout ripple") {
                    MaterialUtils.materialText(this)
                }.lparams(wrapContent, wrapContent) {
                    centerInParent()
                }

                MaterialUtils.rippleThis(
                    this,
                    MaterialUtils.FOREGROUND
                )
                backgroundColorResource = R.color.layoutTransparent

                setOnClickListener {
                    context.toast("ripple")
                }
            }.lparams(matchParent, dip(120)) {
                bottomMargin = dip(12)
            }
        }
    }
}