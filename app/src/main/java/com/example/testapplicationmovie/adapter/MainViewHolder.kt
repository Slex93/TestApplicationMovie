package com.example.testapplicationmovie.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.testapplicationmovie.model.Movie
import com.example.testapplicationmovie.databinding.ItemRecyclerViewBinding
import com.example.testapplicationmovie.utilites.downloadAndSet

class MainViewHolder(private val binding: ItemRecyclerViewBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie){
        binding.itemImageView.downloadAndSet(movie.multimedia.src)
        binding.itemTitle.text = movie.display_title
        binding.itemDescription.text = movie.summary_short
    }

}