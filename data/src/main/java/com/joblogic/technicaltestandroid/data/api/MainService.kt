package com.joblogic.technicaltestandroid.data.api

import com.joblogic.technicaltestandroid.data.reponse.ProductResponse
import com.joblogic.technicaltestandroid.data.reponse.UserResponse
import com.joblogic.technicaltestandroid.domain.models.ProductModel
import retrofit2.http.*


interface MainService {

    @GET("demo-1/buy")
    suspend fun getListProduct(
    ): List<ProductResponse>

    @GET("demo-1/call")
    suspend fun getListUser(
    ): List<UserResponse>

}