/*
 * MaterialUtils.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 10:05 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.app.Activity


object MaterialUtils {

    const val FOREGROUND = 0
    const val BACKGROUND = 1

    fun setLightStatusBar(view: View, activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            activity.window.statusBarColor = Color.WHITE
        }
    }

    fun materialCard(cardView: CardView) {
        cardView.apply {
            radius = 8f
        }
    }

    fun materialText(textView: TextView) {
        textView.apply {
            //typeface = Typeface.SANS_SERIF
            typeface = Typeface.createFromAsset(this.resources.assets, "fonts/SourceSansPro-Regular.ttf")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                letterSpacing = 0.03f
            }
        }
    }

    fun rippleThis(view: View, type: Int) {
        view.apply {
            val rippleValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, rippleValue, true)

            when (type) {
                FOREGROUND -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    foreground = ContextCompat.getDrawable(context, rippleValue.resourceId)
                }
                BACKGROUND -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    background = ContextCompat.getDrawable(context, rippleValue.resourceId)
                }
            }
        }
    }

    fun imageTint(imageView: ImageView, color: Int) {
        imageView.apply {
            setColorFilter(ContextCompat.getColor(context, color),
                android.graphics.PorterDuff.Mode.MULTIPLY)
        }
    }
}