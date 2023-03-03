package com.joblogic.technicaltestandroid.data.repository

import com.joblogic.technicaltestandroid.data.reponse.ProductResponse

interface MainCache {
    suspend fun getProducts(): List<ProductResponse>

    suspend fun insert(productResponse:ProductResponse)

    suspend fun inserts(list:List<ProductResponse>)
}

