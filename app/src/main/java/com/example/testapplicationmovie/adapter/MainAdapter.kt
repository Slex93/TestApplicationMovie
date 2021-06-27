package com.example.testapplicationmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplicationmovie.model.Movie
import com.example.testapplicationmovie.databinding.ItemRecyclerViewBinding

class MainAdapter:RecyclerView.Adapter<MainViewHolder>() {

    private val listOfMovie = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listOfMovie[position])
    }

    override fun getItemCount(): Int = listOfMovie.size

    fun addMovie(movie: Movie){
        listOfMovie.add(movie)
        notifyItemChanged(listOfMovie.size)
    }

}