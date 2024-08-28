package com.example.example_mvvm_api_network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example_mvvm_api_network.data.model.Pokemon
import com.example.example_mvvm_api_network.data.repository.PokemonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepositoryImpl: PokemonRepositoryImpl
) : ViewModel() {

    private var _pokemon = MutableStateFlow(Pokemon())
    val pokemon: StateFlow<Pokemon> get() = _pokemon

    private var pages = 0

    init {
        fetchPokemon()
    }

    private fun fetchPokemon(){
        viewModelScope.launch {
            val result = pokemonRepositoryImpl.getPokemonWithRetrofit(0)
            result.fold(
                onSuccess = {
                    _pokemon.update { it }
                },
                onFailure = {

                }
            )
        }
    }


    fun loadMore(){
        viewModelScope.launch {
            pages ++
            val result = pokemonRepositoryImpl.getPokemonWithRetrofit(20 * pages)
            result.fold(
                onSuccess = {
                    _pokemon.update { it }
                },
                onFailure = {

                }
            )
        }
    }
}