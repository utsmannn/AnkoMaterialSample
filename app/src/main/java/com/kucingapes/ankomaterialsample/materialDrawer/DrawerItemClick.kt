/*
 * DrawerItemClick.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/2/18 5:23 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.materialDrawer

interface DrawerItemClick {
    fun onDrawerClick(position: Int, itemDrawer: ItemDrawer)
}