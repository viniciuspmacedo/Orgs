package com.viniciusmacedo.orgs.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.viniciusmacedo.orgs.databinding.ActivityProductDetailsBinding
import com.viniciusmacedo.orgs.extensions.formatToBRL
import com.viniciusmacedo.orgs.extensions.loadImage
import com.viniciusmacedo.orgs.model.Product

class ProductDetailsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.getParcelableExtra(PRODUCT_KEY, Product::class.java)?.let { product ->
            fillFields(product)
        } ?: finish()
    }

    private fun fillFields(it: Product) {
        binding.apply {
            activityProductDetailsImage
                .loadImage(it.image, this@ProductDetailsActivity)
            activityProductDetailsName.text = it.name
            activityProductDetailsDescription.text = it.description
            activityProductDetailsPrice.text = it.price.formatToBRL()
        }
    }
}
