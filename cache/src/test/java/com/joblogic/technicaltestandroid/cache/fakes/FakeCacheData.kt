package com.joblogic.technicaltestandroid.cache.fakes

import com.joblogic.technicaltestandroid.cache.fakes.FakeValueFactory.randomInt
import com.joblogic.technicaltestandroid.cache.fakes.FakeValueFactory.randomString
import com.joblogic.technicaltestandroid.data.reponse.ProductResponse


object FakeCacheData {

    fun getFakeProductEntity(
        size: Int,
    ): List<ProductResponse> {
        val products = mutableListOf<ProductResponse>()
        repeat(size) {
            products.add(createProductEntity())
        }
        return products
    }

    private fun createProductEntity(): ProductResponse {
        return ProductResponse(

            price = randomInt(),
            quantity = randomInt(),
            id = randomInt(),
            name = randomString(),
            type = randomInt(),
        )
    }
}
