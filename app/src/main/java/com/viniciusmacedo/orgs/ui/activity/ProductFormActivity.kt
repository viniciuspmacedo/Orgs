package com.viniciusmacedo.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.viniciusmacedo.orgs.R
import com.viniciusmacedo.orgs.dao.ProductDao
import com.viniciusmacedo.orgs.databinding.ActivityProductFormBinding
import com.viniciusmacedo.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpSaveButton()
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

        return Product(
            name = name, description = description, price = (value)
        )
    }
}