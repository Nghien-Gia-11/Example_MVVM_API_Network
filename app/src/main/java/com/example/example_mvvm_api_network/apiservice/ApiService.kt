package com.example.example_mvvm_api_network.apiservice

import com.example.example_mvvm_api_network.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon?limit=20")
    suspend fun getPokemon(
        @Query("offset") offset : Int
    ) : Pokemon

}