package com.example.example_mvvm_api_network.model

data class Pokemon(
    val count : Int = 0,
    val next : String = "",
    val previous : String = "",
    val results : MutableList<Result> = mutableListOf()
)
