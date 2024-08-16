package com.example.example_mvvm_api_network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example_mvvm_api_network.instance_retorfit.RetrofitInstance
import com.example.example_mvvm_api_network.model.Pokemon
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private var _pokemon = MutableLiveData<Pokemon>()
    val pokemon : LiveData<Pokemon> get() = _pokemon

    private var pages = 0

    init {
        fetchPokemon()
    }

    private fun fetchPokemon(){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemon(0)
                _pokemon.postValue(response)
            } catch (e : Exception){
                e.printStackTrace()
                Log.e("Exception", e.toString())
            }
        }
    }

    fun loadMore(){
        viewModelScope.launch {
            pages ++
            try {
                val response = RetrofitInstance.api.getPokemon(20 * pages)
                val current = _pokemon.value ?: Pokemon()
                current.results.addAll(response.results)
                _pokemon.postValue(current)
            } catch (e : Exception){
                e.printStackTrace()
                Log.e("Exception", e.toString())
            }
        }
    }
}