/*
 * BaseUi.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 10:04 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.GridLayoutManager
import android.view.ViewManager
import android.widget.TextView
import com.kucingapes.ankomaterialsample.adapter.MainAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

object BaseUi {

    fun ViewManager.baseRecyclerView(items: MutableList<String>) = recyclerView {
        lparams(matchParent, matchParent)
        id = R.id.recycler_view
        clipToPadding = false
        padding = dip(4)

        items.apply {
            add("https://www.thehappycatsite.com/wp-content/uploads/2017/11/funny5.jpg")
            add("https://pbs.twimg.com/profile_images/462066811727380480/xKtH9LRd_400x400.jpeg")
            add("https://1.bp.blogspot.com/-5ySdo4G9z-k/WlZrLJW8agI/AAAAAAAB3D8/sxfRq4whKC8BZoQuQaI_PhOdieSLXFgtwCLcBGAs/s1600/funny-cat-293-01.jpg")
            add("https://boygeniusreport.files.wordpress.com/2016/05/scared-surprised-cat-face.jpg?quality=98&strip=all&w=782")
            add("http://cdn.kickvick.com/wp-content/uploads/2014/07/funny-cats.jpg")
            add("https://pbs.twimg.com/profile_images/683842208500285440/-kb4Pf8k_400x400.jpg")
            add("https://www.sciencenews.org/sites/default/files/styles/article-main-image-large/public/main/blogposts/sci_Grumpy_Cat_by_Gage_Skidmore_free.jpg?itok=PbTDbU0O")
            add("http://fallinpets.com/wp-content/uploads/2016/09/surrender.jpg")
            add("https://images-na.ssl-images-amazon.com/images/I/91HiqSzRYwL._SY550_.jpg")
            add("https://pics.me.me/funny-cat-29087748.png")
            add("https://fallinpets.com/wp-content/uploads/2017/11/FUNNY-CAT-PICTURES.jpg")
            add("https://www.lifewithcats.tv/wp-content/uploads/2018/07/google-1.png")
        }

        /*layoutManager = GridLayoutManager(context, 2)
        adapter = MainAdapter(items)*/
    }

    /*fun ViewManager.baseToolbar(themeRes: Int) = themedToolbar(themeRes) {
        id = R.id.toolbar
        title = resources.getString(R.string.app_name)

        val viewTitle = this.getChildAt(0) as TextView
        MaterialUtils.materialText(viewTitle)
    }*/

    fun ViewManager.baseToolbar() = themedToolbar(R.style.ThemeOverlay_AppCompat_Dark) {
        id = R.id.toolbar
        title = resources.getString(R.string.app_name)

        val viewTitle = this.getChildAt(0) as TextView
        MaterialUtils.materialText(viewTitle)
    }

    fun ViewManager.baseAppBarTop() = appBarLayout {
        lparams(matchParent, wrapContent)
        baseToolbar().lparams(matchParent, wrapContent) {
            scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP or
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
        }
    }

    fun ViewManager.fakeStatusBar(context: Context) = relativeLayout {
        id = R.id.fake_statusbar
        backgroundColorResource = R.color.colorPrimaryDark
        lparams(matchParent, getStatusBarHeight(context))
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}