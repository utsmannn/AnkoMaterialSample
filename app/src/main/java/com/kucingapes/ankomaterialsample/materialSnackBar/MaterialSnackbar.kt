/*
 * MaterialSnackbar.kt on AnkoMaterialSample
 * Developed by Muhammad Utsman
 * Last modified 11/5/18 10:26 PM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankomaterialsample.materialSnackBar

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import com.kucingapes.ankomaterialsample.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class MaterialSnackbar private constructor(context: Context) {
    private var background: Int = 0
    var contentView: View? = null
        private set
    private var duration: LENGTH? = null
    private var swipe: Boolean = false
    private var bottomMargin: Int = 12
    private var snackbar: Snackbar? = null

    private var ctx = context

    val isShowing: Boolean
        get() = snackbar != null && snackbar!!.isShown

    init {
        this.duration = LENGTH.LONG
        this.background = -1
        this.swipe = true
    }


    fun duration(duration: LENGTH): MaterialSnackbar {
        this.duration = duration
        return this
    }

    fun swipe(swipe: Boolean): MaterialSnackbar {
        this.swipe = swipe
        return this
    }

    fun bottomMargin(bottomMargin: Int) : MaterialSnackbar {
        this.bottomMargin = bottomMargin
        return this
    }

    fun build(view: View): MaterialSnackbar {
        when (duration) {
            LENGTH.INDEFINITE -> snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE)
            LENGTH.SHORT -> snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
            LENGTH.LONG -> snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
        }
        val snackbarView = snackbar?.view as Snackbar.SnackbarLayout

        if (!swipe) {
            snackbarView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    snackbarView.viewTreeObserver.removeOnPreDrawListener(this)
                    (snackbarView.layoutParams as CoordinatorLayout.LayoutParams).behavior = null
                    return true
                }
            })
        }

        snackbarView.setPadding(0, 0, 0, 0)
        snackbarView.setBackgroundResource(android.R.color.transparent)
        val text = snackbarView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        text.visibility = View.INVISIBLE
        val action = snackbarView.findViewById<View>(android.support.design.R.id.snackbar_action) as TextView
        action.visibility = View.INVISIBLE
        contentView = SnackBarUi(bottomMargin)
            .createView(AnkoContext.create(ctx, snackbarView))
        snackbarView.addView(contentView, 0)
        return this
    }

    fun setText(text: CharSequence): MaterialSnackbar {
        val textView = contentView?.find<TextView>(R.id.snackbar_text)
        textView?.text = text
        return this
    }

    fun setAction(text: CharSequence, listener: View.OnClickListener): MaterialSnackbar {
        val actionView = contentView?.find<Button>(R.id.snackbar_action)
        actionView?.text = text
        actionView?.visibility = View.VISIBLE
        actionView?.setOnClickListener { view ->
            listener.onClick(view)
            dismiss()
        }
        return this
    }

    fun show() {
        snackbar?.show()
    }

    fun dismiss() {
        if (snackbar != null) snackbar?.dismiss()
    }

    enum class LENGTH {
        INDEFINITE, SHORT, LONG
    }

    companion object {

        fun Builder(context: Context): MaterialSnackbar {
            return MaterialSnackbar(context)
        }
    }
}