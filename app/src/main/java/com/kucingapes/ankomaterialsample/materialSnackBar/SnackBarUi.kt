/*
 * SnackBarUi.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/5/18 10:13 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.materialSnackBar

import android.graphics.Color
import android.support.v7.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.Button
import com.kucingapes.ankomaterialsample.MaterialUtils
import com.kucingapes.ankomaterialsample.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.custom.ankoView

class SnackBarUi(private val marginBottom: Int) : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        relativeLayout {
            id = R.id.snackbar_layout
            lparams(matchParent, wrapContent)

            cardView {
                linearLayout {
                    gravity = Gravity.CENTER_VERTICAL
                    backgroundColor = Color.parseColor("#323232")
                    textView {
                        id = R.id.snackbar_text
                        textColorResource = android.R.color.white
                        MaterialUtils.materialText(this)
                    }.lparams(matchParent, wrapContent) {
                        weight = 3f
                        leftMargin = dip(16)
                        rightMargin = dip(16)
                    }

                    styledButton(R.style.Widget_AppCompat_Button_Borderless) {
                        id = R.id.snackbar_action
                        textColorResource = R.color.colorAccent
                    }.lparams(wrapContent, wrapContent)

                }.lparams(matchParent, wrapContent)

                MaterialUtils.materialCard(this)
            }.lparams(matchParent, wrapContent) {
                leftMargin = dip(12)
                rightMargin = dip(12)
                topMargin = dip(12)
                bottomMargin = dip(marginBottom)
            }
        }
    }

    inline fun ViewManager.styledButton(styleRes: Int = 0, init: Button.() -> Unit): Button = ankoView({
        if (styleRes == 0) Button(it)
        else Button(ContextThemeWrapper(it, styleRes), null, 0)
    }, 0, init)
}