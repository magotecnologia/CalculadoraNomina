package com.magotecnologia.calculadoranomina.ui

import android.widget.ImageView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.magotecnologia.calculadoranomina.R

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