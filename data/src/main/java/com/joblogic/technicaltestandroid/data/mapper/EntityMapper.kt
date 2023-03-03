package com.joblogic.technicaltestandroid.data.mapper

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
