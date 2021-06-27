package com.example.testapplicationmovie

import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.testapplicationmovie.adapter.MainAdapter
import com.example.testapplicationmovie.databinding.ActivityMainBinding
import com.example.testapplicationmovie.model.Movie
import com.example.testapplicationmovie.model.RetrofitRepository
import com.example.testapplicationmovie.viewmodel.MainViewModel
import com.example.testapplicationmovie.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter

    private var isScrolling = false
    private var offset = 0

    private val repository = RetrofitRepository()
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.initRetrofit(offset)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = binding.recyclerView
        adapter = MainAdapter()
        recyclerView.adapter = adapter

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager


        mainViewModel.listOfMovies.observe(this){movie: Movie ->
            adapter.addMovie(movie)
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isScrolling
                    && dy < 0
                ) {
                    updateData()
                }
            }
        })
    }

    private fun updateData() {
        isScrolling = false
        offset+=20
        mainViewModel.initRetrofit(offset)
    }

}