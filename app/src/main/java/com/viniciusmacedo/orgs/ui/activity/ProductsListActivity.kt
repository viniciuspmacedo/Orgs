package com.viniciusmacedo.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.viniciusmacedo.orgs.R
import com.viniciusmacedo.orgs.dao.ProductDao
import com.viniciusmacedo.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ProductsListActivity : AppCompatActivity() {
    private val dao = ProductDao()
    private val adapter = ProductsListAdapter(context = this, products = dao.findAll())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        setUpRecyclerView()
        setUpFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.findAll())
    }

    private fun setUpFab() {
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            goesToProductFormActivity()
        }
    }

    private fun goesToProductFormActivity() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun setUpRecyclerView() {
        val recycleView = findViewById<RecyclerView>(R.id.recyclerview)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)
    }

}