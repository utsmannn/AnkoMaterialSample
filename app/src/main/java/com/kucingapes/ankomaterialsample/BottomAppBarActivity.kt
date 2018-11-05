/*
 * BottomAppBarActivity.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/5/18 11:03 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample

import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.design.coordinatorLayout
import com.kucingapes.ankomaterialsample.BaseUi.fakeStatusBar
import com.kucingapes.ankomaterialsample.BaseUi.baseToolbar
import com.kucingapes.ankomaterialsample.materialSnackBar.MaterialSnackbar
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.navigationIconResource
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.cardview.v7.cardView

class BottomAppBarActivity : BaseActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var toolbarBottom: Toolbar
    private lateinit var actionCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BottomAppBarActivityUi().setContentView(this)
        MaterialUtils.setLightStatusBar(window.decorView, this)

        toolbar = find(R.id.toolbar)
        toolbarBottom = find(R.id.bottom_toolbar)
        actionCard = find(R.id.card_appbar)

        initToolbar()
        initBottomAppbar()
    }

    private fun initBottomAppbar() {
        toolbarBottom.inflateMenu(R.menu.bottom_menu)
        toolbarBottom.navigationIconResource = R.drawable.ic_menu

        val menuBottomSearch = toolbarBottom.menu.findItem(R.id.action_search)
        menuBottomSearch.setOnMenuItemClickListener {
            toast("search menu bottom")
            true
        }
        toolbarBottom.setNavigationOnClickListener {
            toast("navigation bottom")
        }

        actionCard.setOnClickListener {
            MaterialSnackbar.Builder(this)
                .bottomMargin(100)
                .build(find(R.id.snackbar_parent))
                .setText("ini snackbar !")
                .show()
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.title = ""
        toolbar.navigationIconResource = R.drawable.ic_back
        toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    class BottomAppBarActivityUi : AnkoComponent<BottomAppBarActivity> {
        override fun createView(ui: AnkoContext<BottomAppBarActivity>): View = with(ui) {
            verticalLayout {
                fakeStatusBar(context).apply {
                    backgroundColorResource =
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) android.R.color.transparent
                            else R.color.colorPrimaryDark

                }

                baseToolbar().apply {
                    backgroundColorResource = android.R.color.transparent
                    setTitleTextColor(resources.getColor(R.color.colorPrimary))
                }
                coordinatorLayout {

                    imageView {
                        imageResource = R.drawable.ic_launcher_foreground
                        MaterialUtils.imageTint(this, R.color.colorPrimary)
                    }.lparams(dip(400), dip(400)) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    coordinatorLayout {
                        id = R.id.snackbar_parent
                    }.lparams(matchParent, matchParent)

                    verticalLayout {
                        id = R.id.bottom_appbar
                        view {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                background = ContextCompat.getDrawable(context, R.drawable.shadow)
                            }
                        }.lparams(matchParent, dip(5))

                        toolbar {
                            id = R.id.bottom_toolbar
                            backgroundColorResource = R.color.colorPrimary
                        }

                    }.lparams(matchParent, wrapContent) {
                        gravity = Gravity.BOTTOM
                    }

                    cardView {
                        radius = 50f
                        id = R.id.card_appbar

                        MaterialUtils.rippleThis(this, MaterialUtils.FOREGROUND)
                        setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))

                        textView("open snackbar !") {
                            MaterialUtils.materialText(this)
                            textColorResource = android.R.color.white
                        }.lparams {
                            margin = dip(16)
                        }
                    }.lparams(wrapContent, wrapContent) {
                        anchorId = R.id.bottom_appbar
                        anchorGravity = Gravity.CENTER_HORIZONTAL
                    }

                }.lparams(matchParent, matchParent)
            }
        }
    }
}