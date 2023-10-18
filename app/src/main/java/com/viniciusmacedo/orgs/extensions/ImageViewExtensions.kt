package com.viniciusmacedo.orgs.extensions

import android.content.Context
import android.os.Build
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.viniciusmacedo.orgs.R

fun ImageView.loadImage(url: String?, context: Context) {

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    load(imageLoader = imageLoader, data = url) {
        fallback(R.drawable.error)
        error(R.drawable.error)
        placeholder(R.drawable.placeholder)
    }
}