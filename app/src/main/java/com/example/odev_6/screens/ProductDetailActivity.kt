package com.example.odev_6.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.odev_6.R
import com.example.odev_6.databinding.ActivityProductDetailBinding
import com.example.odev_6.models.Product

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getParcelableExtra<Product>("product")

        if(product != null){
            Glide.with(this).load(product.images[0]).into(binding.imgProductImage)
            binding.txtTitle.text = product.title
            binding.txtDescription.text = product.description
            binding.txtPrice.text = "Price: " + product.price.toString() + " $"
            binding.txtRate.text = product.rating.toString()
            binding.txtBrand.text = product.brand
            binding.txtStock.text = "Stock: " + product.stock
        }
    }
}