package com.example.example_mvvm_api_network

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.example_mvvm_api_network.databinding.ActivityMainBinding
import com.example.example_mvvm_api_network.model.Pokemon

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokemonViewModel by viewModels()

    private lateinit var pokemonAdapter: PokemonAdapter
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPokemonAdapter()
        viewModel.pokemon.observe(this) {
            pokemonAdapter.setPokemon(it)
            isLoading = false
        }
        loadMore()
    }

    private fun loadMore() {
        binding.rvPokemon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                val visibleIteCount = recyclerView.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItem = layoutManager.findFirstVisibleItemPositions(null)[0]
                if (!isLoading && (visibleIteCount + pastVisibleItem) >= totalItemCount) {
                    isLoading = true
                    viewModel.loadMore()
                }
            }
        })
    }

    private fun setPokemonAdapter() {
        pokemonAdapter = PokemonAdapter(this, Pokemon())
        binding.rvPokemon.apply {
            adapter = pokemonAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}