package com.joblogic.technicaltestandroid.domain.fakes

import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.models.ProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getProducts(): Flow<Resource<List<ProductModel>>>  = flow{   listOf(
        ProductModel(
            id = 6,
            name = "Cocos",
            price = 200000,
            quantity = 3,
            type = 2,
        ),
        ProductModel(
            id = 7,
            name = "React Native",
            price = 200000,
            quantity = 3,
            type = 2,
        ),
        ProductModel(
            id = 8,
            name = "Flutter",
            price = 200000,
            quantity = 3,
            type = 2,
        ),
        ProductModel(
            id = 9,
            name = "IOS",
            price = 200000,
            quantity = 3,
            type = 2,
        ),
        ProductModel(
            id = 10,
            name = "Android",
            price = 200000,
            quantity = 3,
            type = 2,
        )
    )
}
}