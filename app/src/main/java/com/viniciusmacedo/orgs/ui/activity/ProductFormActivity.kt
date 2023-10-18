package com.viniciusmacedo.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.viniciusmacedo.orgs.dao.ProductDao
import com.viniciusmacedo.orgs.databinding.ActivityProductFormBinding
import com.viniciusmacedo.orgs.extensions.loadImage
import com.viniciusmacedo.orgs.model.Product
import com.viniciusmacedo.orgs.ui.dialog.DialogImageForm
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Produto"
        setUpSaveButton()
        binding.activityProductFormImage.setOnClickListener {
            DialogImageForm(this).show(url){ image ->
                url = image
                binding.activityProductFormImage.loadImage(url, this)
            }
        }
    }



    private fun setUpSaveButton() {
        val dao = ProductDao()
        val saveButton = binding.activityProductFormSaveButton
        saveButton.setOnClickListener {
            val newProduct = createNewProduct()
            dao.add(newProduct)
            finish()
        }
    }

    private fun createNewProduct(): Product {
        val nameField = binding.activityProductFormName
        val name = nameField.text.toString()
        val descriptionField = binding.activityProductFormDescription
        val description = descriptionField.text.toString()
        val valueField = binding.activityProductFormValue
        val valueText = valueField.text.toString()
        val value = if (valueText.isBlank()) BigDecimal.ZERO else BigDecimal(valueText)
        val imageUrl = url

        return Product(
            name = name, description = description, price = (value), image = imageUrl
        )
    }
}