package com.tiranga.acharyaattendence.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetroAPI {

    @FormUrlEncoded
    @POST("update.php")
    suspend fun updateApi(@Field("data") requestBody: String): Response<ResponseBody>
}