package com.example.example_mvvm_api_network.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example_mvvm_api_network.R
import com.example.example_mvvm_api_network.databinding.LayoutItemPokemonBinding
import com.example.example_mvvm_api_network.data.model.Pokemon
import java.lang.ref.WeakReference

class PokemonAdapter(private val context: Context, private var pokemon: Pokemon) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val contextRef = WeakReference(context)

    private val colors: List<Int> = listOf(
        R.color.blue,
        R.color.pink,
        R.color.yellow,
    )
    private val urlImg =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

    class ViewHolder(var binding: LayoutItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = contextRef.get()
        val view = LayoutInflater.from(context)
        val binding = LayoutItemPokemonBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pokemon.results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.text = pokemon.results[position].name
        val url = pokemon.results[position].url.split("/")
        val numberItem = url[url.size - 2]
        holder.binding.tvNumber.text = numberItem
        val randomColor = colors.random()
        holder.binding.layoutItemPokemon.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(context, randomColor))
        Glide
            .with(holder.itemView.context)
            .load("$urlImg$numberItem.png")
            .into(holder.binding.imgPokemon)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPokemon(pokemon: Pokemon) {
        this.pokemon = Pokemon()
        this.pokemon = pokemon
        notifyDataSetChanged()
    }


}