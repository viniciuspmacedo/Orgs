package com.viniciusmacedo.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.viniciusmacedo.orgs.R
import com.viniciusmacedo.orgs.dao.ProductDao
import com.viniciusmacedo.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_form)

        setUpSaveButton()
    }

    private fun setUpSaveButton() {
        val dao = ProductDao()
        val saveButton = findViewById<Button>(R.id.activity_product_form_save_button)
        saveButton.setOnClickListener {
            val newProduct = createNewProduct()
            dao.add(newProduct)
            finish()
        }
    }

    private fun createNewProduct(): Product {
        val nameField = findViewById<EditText>(R.id.activity_product_form_name)
        val name = nameField.text.toString()
        val descriptionField = findViewById<EditText>(R.id.activity_product_form_description)
        val description = descriptionField.text.toString()
        val valueField = findViewById<EditText>(R.id.activity_product_form_value)
        val valueText = valueField.text.toString()
        val value = if (valueText.isBlank()) BigDecimal.ZERO else BigDecimal(valueText)

        return Product(
            name = name, description = description, price = (value)
        )
    }
}