package com.example.example_mvvm_api_network.data.repository

import android.util.Log
import com.example.example_mvvm_api_network.apiservice.ApiService
import com.example.example_mvvm_api_network.data.model.Pokemon
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(private val apiService: ApiService) : PokemonRepository {
/*    override suspend fun getPokemonWithKtor(): Result<Pokemon> =
        runCatching {
    }*/

    override suspend fun getPokemonWithRetrofit(pos : Int): Result<Pokemon> =
        runCatching {
            apiService.getPokemon(pos)
        }

}