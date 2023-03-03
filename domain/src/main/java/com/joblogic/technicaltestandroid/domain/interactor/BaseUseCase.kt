package com.joblogic.technicaltestandroid.domain.interactor

interface BaseUseCase<in Parameter, out Result> {
   suspend fun execute (params: Parameter): Result
}
