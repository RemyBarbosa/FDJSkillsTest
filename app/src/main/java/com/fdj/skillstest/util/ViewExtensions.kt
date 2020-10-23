package com.fdj.skillstest.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.fdj.skillstest.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.transparent_grey)))
        .into(this)
}

fun ViewGroup.inflateFromParent(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun TextView.setTextOrInvisible(text: String?) {
    if (text.isNullOrBlank()) this.visibility = View.INVISIBLE else this.text = text
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}