/*
 * F_Typo.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/5/18 1:00 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kucingapes.ankomaterialsample.MaterialUtils
import com.kucingapes.ankomaterialsample.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class F_Typo : Fragment(), AnkoComponent<Context> {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        nestedScrollView {
            verticalLayout {
                padding = dip(12)
                textView(R.string.lorem_five_word) {
                    MaterialUtils.materialText(this)
                    textSize = 30f
                    textColorResource = R.color.colorAccent
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(12)
                }

                textView(R.string.lorem_one_paragh) {
                    MaterialUtils.materialText(this)
                    textSize = 20f
                    textColorResource = R.color.colorPrimary
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(12)
                }

                textView(R.string.lorem_two_paragh) {
                    MaterialUtils.materialText(this)
                }.lparams(matchParent, wrapContent)
            }.lparams(matchParent, wrapContent)
        }
    }
}