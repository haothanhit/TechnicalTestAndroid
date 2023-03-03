package com.joblogic.technicaltestandroid.data.fakes

import com.joblogic.technicaltestandroid.data.reponse.ProductResponse

object FakeProducts {
    fun getProducts(): List<ProductResponse> = listOf(
        ProductResponse(
            id = 1,
            name = "Cocos",
            price = 200000,
            quantity = 3,
            type = 2,
        ),
        ProductResponse(
            id = 2,
            name = "React Native",
            price = 200000,
            quantity = 3,
            type = 2,
        ),
        ProductResponse(
            id = 3,
            name = "Flutter",
            price = 200000,
            quantity = 3,
            type = 2,
        ),
        ProductResponse(
            id = 4,
            name = "IOS",
            price = 200000,
            quantity = 3,
            type = 2,
        ),
        ProductResponse(
            id = 5,
            name = "Android",
            price = 200000,
            quantity = 3,
            type = 2,
        )
    )

}
