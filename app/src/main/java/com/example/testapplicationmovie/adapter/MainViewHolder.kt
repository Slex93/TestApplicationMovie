package com.example.testapplicationmovie.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.testapplicationmovie.Movie
import com.example.testapplicationmovie.databinding.ItemRecyclerViewBinding

class MainViewHolder(private val binding: ItemRecyclerViewBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie){
        binding.itemTitle.text = movie.title
        binding.itemDescription.text = movie.description

    }

}