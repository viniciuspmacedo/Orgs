package com.viniciusmacedo.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.viniciusmacedo.orgs.databinding.ImageFormBinding
import com.viniciusmacedo.orgs.extensions.loadImage

class DialogImageForm(private val context: Context) {
    fun show(defaultUrl: String?, onImageLoaded: (String) -> Unit) {
        ImageFormBinding.inflate(LayoutInflater.from(context)).apply {

            defaultUrl?.let {
                imageFormImageview.loadImage(it, context)
                imageFormUrl.setText(it)
            }

            imageFormLoadButton.setOnClickListener {
                val url = imageFormUrl.text.toString()
                imageFormImageview.loadImage(url, context)
            }
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = imageFormUrl.text.toString()
                    onImageLoaded(url)
                }
                .setNegativeButton("Cancel") { _, _ ->

                }
                .show()
        }
        }


}