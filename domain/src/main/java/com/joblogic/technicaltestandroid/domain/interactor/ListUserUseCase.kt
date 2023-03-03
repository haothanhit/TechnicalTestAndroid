package com.joblogic.technicaltestandroid.domain.interactor

import com.joblogic.technicaltestandroid.domain.Resource
import com.joblogic.technicaltestandroid.domain.models.UserModel
import com.joblogic.technicaltestandroid.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias ListUserBaseUseCase = BaseUseCase<Unit, Flow<Resource<List<UserModel>>>>

class ListUserUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : ListUserBaseUseCase {

    override suspend fun execute(params: Unit) = mainRepository.getListUser()
}
