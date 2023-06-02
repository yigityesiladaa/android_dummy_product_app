package com.example.odev_6.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.odev_6.R
import com.example.odev_6.models.DummyProduct
import com.example.odev_6.models.Product

class CustomProductsAdapter(private val context : Activity)
    :ArrayAdapter<Product>(context,R.layout.list_item){

    private var productList = listOf<Product>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = LayoutInflater.from(context).inflate(R.layout.list_item,null,true)

        val productImageView = rootView.findViewById<ImageView>(R.id.imgProductImageView)
        val productTitle = rootView.findViewById<TextView>(R.id.txtProductTitle)
        val productPrice = rootView.findViewById<TextView>(R.id.txtProductPrice)
        val productRate = rootView.findViewById<TextView>(R.id.txtProductRate)

        val product = productList[position]

        Glide.with(rootView).load(product.thumbnail).into(productImageView)
        productTitle.text = product.title
        productPrice.text = product.price.toString() + " $"
        productRate.text = product.rating.toString()


        return rootView
    }

    override fun getCount(): Int {
        return productList.count()
    }

    fun submitList(list : List<Product>){
        productList = list
        notifyDataSetChanged()
    }
}