package com.mt.android.data.network

import com.mt.android.data.model.MainList
import retrofit2.http.*


interface MyApi {
     @GET("api")
    suspend fun getImageDataList(
        @Query("key") key: String,
        @Query("q") searchValue: String,
        @Query("image_type") image_type: String,
        @Query("pretty") pretty: String,
    ): MainList


}