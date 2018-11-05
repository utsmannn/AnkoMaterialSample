/*
 * ItemDrawer.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/4/18 11:24 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.materialDrawer

class ItemDrawer {
    var icon: Int = -1
    var title: String = ""
    var divider: Boolean = false
    var identifier: Int = -1
    var tag: String = ""
    var focus: Boolean = true

    constructor(title: String) {
        this.title = title
    }

    constructor(icon: Int, title: String) {
        this.icon = icon
        this.title = title
    }

    constructor(divider: Boolean) {
        this.divider = divider
    }

    fun addIdentifier(identifier: Int): ItemDrawer {
        this.identifier = identifier
        return this
    }

    fun addTag(tag: String): ItemDrawer {
        this.tag = tag
        return this
    }

    fun setFocusable(focus: Boolean): ItemDrawer {
        this.focus = focus
        return this
    }

    companion object {
        const val DIVIDER = true
    }



}