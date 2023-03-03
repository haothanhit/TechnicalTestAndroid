package com.joblogic.technicaltestandroid.domain.models

data class UserModel(
    val id: Int,
    val name: String,
    val number: String
){
    override fun toString(): String {
        return "Name: $name\n\nNumber: $number"
    }
}
