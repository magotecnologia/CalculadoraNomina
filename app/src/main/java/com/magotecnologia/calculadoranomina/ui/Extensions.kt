package com.magotecnologia.calculadoranomina.ui

import android.view.View
import android.widget.ImageView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.magotecnologia.calculadoranomina.R
import java.text.NumberFormat

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Grupo de funciones de extensión para la UI
 */


fun ImageView.loadImage(url: String?) {
    this.load(url) {
        crossfade(enable = true)
        placeholder(R.drawable.ic_launcher_foreground)
        error(android.R.drawable.gallery_thumb)
        transformations(RoundedCornersTransformation(2F))
    }
}

fun Int.toMoneyString() =
    "$${NumberFormat.getIntegerInstance().format(this)}"

fun View.changeVisibility() {
    this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
}