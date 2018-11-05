/*
 * ItemMainUi.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 10:31 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.ankoUi

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kucingapes.ankomaterialsample.MaterialUtils
import com.kucingapes.ankomaterialsample.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ItemMainUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            MaterialUtils.materialCard(this)

            imageView {
                id = R.id.cat_image
                scaleType = ImageView.ScaleType.CENTER_CROP
                MaterialUtils.rippleThis(
                    this,
                    MaterialUtils.FOREGROUND
                )
            }.lparams(matchParent, matchParent)

            lparams(matchParent, dip(100)) {
                margin = dip(6)
            }
        }
    }
}