package com.viniciusmacedo.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.viniciusmacedo.orgs.R
import com.viniciusmacedo.orgs.dao.ProductDao
import com.viniciusmacedo.orgs.databinding.ActivityProductsListBinding
import com.viniciusmacedo.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ProductsListActivity : AppCompatActivity() {
    private val dao = ProductDao()
    private val adapter = ProductsListAdapter(context = this, products = dao.findAll())

    private val binding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.findAll())
    }

    private fun setUpFab() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            goesToProductFormActivity()
        }
    }

    private fun goesToProductFormActivity() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun setUpRecyclerView() {
        val recycleView = binding.recyclerview
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(this)
    }

}