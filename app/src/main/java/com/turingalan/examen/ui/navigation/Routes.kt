package com.turingalan.examen.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes() {
    @Serializable
    object Search:Routes()
    @Serializable
    data class Result(val search:String): Routes()
    @Serializable
    data class Detail(val id:String):Routes()
}