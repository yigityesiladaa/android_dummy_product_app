package com.example.odev_6.configs
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private const val Base_URL = "https://dummyjson.com/"
    private var retrofit : Retrofit? = null

    //Customized TimeOut
    private val client = OkHttpClient.Builder()
        .readTimeout(60,TimeUnit.SECONDS)
        .build()

    fun getClient() : Retrofit{
        if(retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl(Base_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }

}