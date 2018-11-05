/*
 * MainUi.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 11:05 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.ankoUi

import android.graphics.Typeface
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import com.kucingapes.ankomaterialsample.BaseUi
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.drawerLayout
import com.kucingapes.ankomaterialsample.BaseUi.fakeStatusBar
import com.kucingapes.ankomaterialsample.BaseUi.baseToolbar
import com.kucingapes.ankomaterialsample.BaseUi.baseAppBarTop
import com.kucingapes.ankomaterialsample.BaseUi.getStatusBarHeight
import com.kucingapes.ankomaterialsample.MainActivity
import com.kucingapes.ankomaterialsample.MaterialUtils
import com.kucingapes.ankomaterialsample.R


class MainUi : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui){
        drawerLayout {
            id = R.id.drawer_layout

            verticalLayout {
                fakeStatusBar(context)
                coordinatorLayout {
                    id = R.id.parent_coordinator
                    baseAppBarTop()

                    frameLayout {
                        id = R.id.container_fragment
                    }.lparams(matchParent, matchParent) {
                        behavior = AppBarLayout.ScrollingViewBehavior()
                    }
                }

            }

            navigationView {
                id = R.id.navigation_view
                verticalLayout {
                    view {
                        backgroundColorResource = R.color.drawerStatusBar
                    }.lparams(matchParent, getStatusBarHeight(context))

                    linearLayout {
                        gravity = Gravity.CENTER
                        linearLayout {
                            textView("Anko") {
                                id = R.id.anko_text
                                typeface = Typeface.SANS_SERIF
                                typeface = Typeface.DEFAULT_BOLD
                                textSize = 50f
                            }
                            imageView {
                                imageResource = R.drawable.kotlin_logo
                            }.lparams(dip(15), dip(15)) {
                                topMargin = dip(10)
                            }
                        }

                        imageView {
                            imageResource = R.drawable.ic_love
                        }.lparams(dip(25), dip(25)) {
                            margin = dip(20)
                        }

                        imageView {
                            imageResource = R.drawable.google_material_logo
                        }.lparams(dip(50), dip(50))
                    }.lparams(matchParent, dip(100))

                    recyclerView {
                        id = R.id.item_navigation
                        layoutManager = LinearLayoutManager(context)
                    }
                }

                relativeLayout {
                    gravity = Gravity.BOTTOM

                    linearLayout {
                        padding = dip(12)
                        id = R.id.github
                        MaterialUtils.rippleThis(this, MaterialUtils.FOREGROUND)
                        gravity = Gravity.CENTER
                        textView("Source code by kucingapes") {
                            MaterialUtils.materialText(this)
                        }

                        imageView {
                            imageResource = R.drawable.social_github
                        }.lparams(dip(25), dip(25)) {
                            leftMargin = dip(12)
                        }
                    }.lparams(matchParent, wrapContent)
                }
            }.lparams(matchParent, matchParent) {
                gravity = Gravity.START
            }
        }
    }
}