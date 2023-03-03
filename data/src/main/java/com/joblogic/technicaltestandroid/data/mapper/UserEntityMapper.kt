package com.joblogic.technicaltestandroid.data.mapper

import com.joblogic.technicaltestandroid.data.reponse.UserResponse
import com.joblogic.technicaltestandroid.domain.models.UserModel
import javax.inject.Inject

class UserEntityMapper @Inject constructor() :
    EntityMapper<UserResponse, UserModel> {
    override fun mapFromModel(model: UserResponse): UserModel {
        return UserModel(
            id = model.id,
            name = model.name,
            number = model.number
        )
    }
}


