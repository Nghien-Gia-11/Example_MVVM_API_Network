package com.example.example_mvvm_api_network.instance_retorfit

import com.example.example_mvvm_api_network.apiservice.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    private const val URL = "https://pokeapi.co/api/v2/pokemon?limit=20&offset=0"

    @Provides
    @Singleton
    private fun retrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    /*private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/

    @Provides
    @Singleton
    fun apiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }
}