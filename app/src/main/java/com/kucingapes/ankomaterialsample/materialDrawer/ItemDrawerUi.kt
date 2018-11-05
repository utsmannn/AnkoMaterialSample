/*
 * ItemDrawerUi.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 11:25 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.materialDrawer

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.kucingapes.ankomaterialsample.MaterialUtils
import com.kucingapes.ankomaterialsample.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ItemDrawerUi : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        relativeLayout {
            cardView {
                lparams(matchParent, wrapContent) {
                    leftMargin = dip(-25)
                    rightMargin = dip(25)

                }
                cardElevation = 0f

                radius = 50f
                id = R.id.drawer_card_container
                setCardBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
                MaterialUtils.rippleThis(
                    this,
                    MaterialUtils.FOREGROUND
                )

                linearLayout {
                    imageView {
                        id = R.id.drawer_item_icon
                        MaterialUtils.imageTint(
                            this,
                            R.color.colorPrimary
                        )
                    }.lparams(dip(25), dip(25)) {
                        margin = dip(12)
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    textView {
                        id = R.id.drawer_item_text
                        MaterialUtils.materialText(this)
                        textColorResource = R.color.colorPrimary
                        maxLines = 1
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams(matchParent, wrapContent) {
                        leftMargin = dip(12)
                        gravity = Gravity.CENTER_VERTICAL
                    }
                }.lparams(matchParent, wrapContent) {
                    leftMargin = dip(25)
                }
            }

            view {
                id = R.id.drawer_divider
                backgroundColor = Color.parseColor("#3f797979")
            }.lparams(matchParent, dip(1)) {
                topMargin = dip(6)
                bottomMargin = dip(6)
                centerInParent()
            }
        }
    }
}