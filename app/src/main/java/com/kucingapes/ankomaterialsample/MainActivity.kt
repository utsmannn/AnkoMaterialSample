/*
 * MainActivity.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 9:41 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.kucingapes.ankomaterialsample.materialDrawer.DrawerAdapter
import com.kucingapes.ankomaterialsample.materialDrawer.DrawerItemClick
import com.kucingapes.ankomaterialsample.materialDrawer.ItemDrawer
import com.kucingapes.ankomaterialsample.ankoUi.MainUi
import com.kucingapes.ankomaterialsample.fragment.F_Main
import com.kucingapes.ankomaterialsample.fragment.F_Ripple
import com.kucingapes.ankomaterialsample.fragment.F_Typo
import com.kucingapes.ankomaterialsample.materialSnackBar.MaterialSnackbar
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.navigationIconResource

class MainActivity : BaseActivity() {
    private var itemNavigation: MutableList<ItemDrawer> = mutableListOf()

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navigationList: RecyclerView

    private val listener = object : DrawerItemClick {
        override fun onDrawerClick(position: Int, itemDrawer: ItemDrawer) {

            Handler().postDelayed({
                drawerLayout.closeDrawers()
            }, 50)

            when (itemDrawer.identifier) {
                MAIN -> replaceFragment(F_Main(), getString(R.string.app_name))
                TYPOGRAPHY -> replaceFragment(F_Typo(), itemDrawer.title)
                RIPPLE -> replaceFragment(F_Ripple(), itemDrawer.title)
                SNACKBAR -> {
                    MaterialSnackbar.Builder(this@MainActivity)
                        .duration(MaterialSnackbar.LENGTH.INDEFINITE)
                        .build(find(R.id.parent_coordinator))
                        .apply {
                            setText("ini material snackbar")
                            setAction("okeh", View.OnClickListener {
                                MaterialSnackbar.Builder(this@MainActivity)
                                    .duration(MaterialSnackbar.LENGTH.SHORT)
                                    .build(find(R.id.parent_coordinator))
                                    .setText("okeh")
                                    .show()
                            })
                        }.show()
                }
                BOTTOMAPPBAR -> startActivity<BottomAppBarActivity>()
                CARDVIEW -> replaceFragment(F_Main(), "CardView with radius = 8f")
                PROGRESSBAR -> {
                    val progressHorizontal = find(R.id.horizontal_progress) as ProgressBar
                    progressHorizontal.visibility = View.VISIBLE
                    Handler().postDelayed({
                        progressHorizontal.visibility = View.GONE
                    }, 10000)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainUi().setContentView(this)
        bindId()
        setupToolbar()
        setupDrawer()

        addFragment(F_Main(), getString(R.string.app_name))
        initGithubLink()
    }

    private fun initGithubLink() {
        val view: LinearLayout = find(R.id.github)
        view.setOnClickListener {
            browse("https://github.com/kucingapes/AnkoMaterialSample")
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.navigationIconResource = R.drawable.ic_menu
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(Gravity.START)
        }
    }

    private fun setupDrawer() {
        val main = ItemDrawer(R.drawable.ic_home, "Main")
            .addIdentifier(MAIN)

        val typography = ItemDrawer(R.drawable.ic_typography, "Typography")
            .addIdentifier(TYPOGRAPHY)

        val ripple = ItemDrawer(R.drawable.ic_finger, "Ripple")
            .addIdentifier(RIPPLE)

        val bottomappbar = ItemDrawer(R.drawable.ic_bottom_appbar, "Bottom AppBar")
            .addIdentifier(BOTTOMAPPBAR)

        val progressbar = ItemDrawer(R.drawable.ic_progressbar, "ProgressBar Toolbar")
            .addIdentifier(PROGRESSBAR)
            .setFocusable(false)

        val cardview = ItemDrawer(R.drawable.ic_card, "CardView")
            .addIdentifier(CARDVIEW)

        val snackbar = ItemDrawer(R.drawable.ic_snackbar, "Snackbar Material")
            .addIdentifier(SNACKBAR)
            .setFocusable(false)

        val divider = ItemDrawer(ItemDrawer.DIVIDER)

        itemNavigation.apply {
            add(divider)
            add(main)
            add(typography)
            add(ripple)
            add(divider)
            add(snackbar)
            add(bottomappbar)
            add(progressbar)
            add(cardview)
        }

        navigationList.adapter = DrawerAdapter(itemNavigation, listener)
    }

    private fun bindId() {
        toolbar = find(R.id.toolbar)
        drawerLayout = find(R.id.drawer_layout)
        navigationView = find(R.id.navigation_view)
        navigationList = find(R.id.item_navigation)
    }


    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    private fun AppCompatActivity.addFragment(fragment: Fragment,title: String? = null, backStackTag: String? = null) {
        supportFragmentManager.inTransaction {
            add(R.id.container_fragment, fragment)
            backStackTag?.let { addToBackStack(fragment.javaClass.name) }
        }

        toolbar.title = title
    }

    private fun AppCompatActivity.replaceFragment(fragment: Fragment, title: String? = null, backStackTag: String? = null) {
        supportFragmentManager.inTransaction {
            replace(R.id.container_fragment, fragment)
            backStackTag?.let { addToBackStack(fragment.javaClass.name) }
        }

        toolbar.title = title
    }

    companion object {
        const val MAIN = 0
        const val TYPOGRAPHY = 1
        const val RIPPLE = 2
        const val PROGRESSBAR = 3
        const val CARDVIEW = 4
        const val BOTTOMAPPBAR = 5
        const val SNACKBAR = 6
    }

}
