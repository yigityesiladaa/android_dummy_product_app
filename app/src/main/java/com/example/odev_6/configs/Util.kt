package com.example.odev_6.configs

import com.example.odev_6.models.DummyProduct
import com.example.odev_6.models.JWTData
import com.example.odev_6.models.Product


class Util {

    companion object {
        var user : JWTData? = null
        var products = listOf<Product>()
    }

}