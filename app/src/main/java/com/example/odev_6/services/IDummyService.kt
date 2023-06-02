package com.example.odev_6.services

import com.example.odev_6.models.DummyProduct
import com.example.odev_6.models.JWTData
import com.example.odev_6.models.JWTUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IDummyService {

    @POST("/auth/login")
    fun login(@Body jwtUser: JWTUser) : Call<JWTData>

    @GET("products")
    fun products(@Query("limit") limit : Int) : Call<DummyProduct>

    @GET("products/search")
    fun filterProducts(@Query("q") filterText : String) : Call<DummyProduct>

}