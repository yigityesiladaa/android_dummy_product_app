package com.example.odev_6.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.odev_6.adapters.CustomProductsAdapter
import com.example.odev_6.configs.ApiClient
import com.example.odev_6.configs.Util
import com.example.odev_6.databinding.ActivityProductsBinding
import com.example.odev_6.models.DummyProduct
import com.example.odev_6.models.Product
import com.example.odev_6.services.IDummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private lateinit var dummyService: IDummyService
    private lateinit var customProductsAdapter: CustomProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        customProductsAdapter = CustomProductsAdapter(this)
        binding.productsListView.adapter = customProductsAdapter

        dummyService = ApiClient.getClient().create(IDummyService::class.java)

        getProducts()

        binding.btnFilter.setOnClickListener(btnFilterOnClickListener)

        binding.productsListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,ProductDetailActivity::class.java)
            intent.putExtra("product",Util.products[position])
            startActivity(intent)
        }

    }

    private fun getProducts(){
        dummyService.products(10).enqueue(object : Callback<DummyProduct> {
            override fun onResponse(call: Call<DummyProduct>, response: Response<DummyProduct>) {
                val response = response.body()
                if(response != null){
                    Util.products = response.products
                    customProductsAdapter.submitList(Util.products)
                }
            }

            override fun onFailure(call: Call<DummyProduct>, t: Throwable) {
                Toast.makeText(this@ProductsActivity,"Products Failure",Toast.LENGTH_LONG).show()
            }

        })
    }

    private val btnFilterOnClickListener = View.OnClickListener {
        var filterText = binding.etSearchProduct.text.toString()
        dummyService.filterProducts(filterText).enqueue(object : Callback<DummyProduct>{
            override fun onResponse(call: Call<DummyProduct>, response: Response<DummyProduct>) {
                val response = response.body()
                if(response != null){
                    Util.products = response.products
                    for(product in Util.products){
                        Log.d("product",product.title)
                    }
                    customProductsAdapter.submitList(Util.products)
                }
            }

            override fun onFailure(call: Call<DummyProduct>, t: Throwable) {
                Toast.makeText(this@ProductsActivity,"Products Filter Failure",Toast.LENGTH_LONG).show()
            }

        })
    }


}