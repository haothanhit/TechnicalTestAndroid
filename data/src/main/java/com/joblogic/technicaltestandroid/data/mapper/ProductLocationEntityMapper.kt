package com.joblogic.technicaltestandroid.data.mapper

import com.joblogic.technicaltestandroid.data.reponse.ProductResponse
import com.joblogic.technicaltestandroid.domain.models.ProductModel
import javax.inject.Inject

class ProductLocationEntityMapper @Inject constructor() :
    EntityMapper<ProductModel, ProductResponse> {

    override fun mapFromModel(model: ProductModel): ProductResponse {
        return ProductResponse(
            id = model.id,
            name = model.name,
            price = model.price,
            quantity = model.quantity,
            type = model.type
        )
    }
}
