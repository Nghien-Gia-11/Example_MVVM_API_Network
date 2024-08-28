package com.example.example_mvvm_api_network.data.repository

import com.example.example_mvvm_api_network.data.model.Pokemon

interface PokemonRepository {
//    suspend fun getPokemonWithKtor() : Result<Pokemon>
    suspend fun getPokemonWithRetrofit(pos : Int) : Result<Pokemon>
}