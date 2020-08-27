package com.example.codingtest.api

import androidx.collection.ArrayMap
import com.example.codingtest.network.json_response


import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

    /**
     * HomeFeed APIs
     */



    @GET("5ec39cba300000720039c1f6")
    fun getproducts(): Single<json_response>








}