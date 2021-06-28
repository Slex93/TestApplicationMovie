package com.example.testapplicationmovie

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.testapplicationmovie.adapter.MainAdapter
import com.example.testapplicationmovie.databinding.ActivityMainBinding
import com.example.testapplicationmovie.model.Movie
import com.example.testapplicationmovie.model.RetrofitRepository
import com.example.testapplicationmovie.viewmodel.MainViewModel
import com.example.testapplicationmovie.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar

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
        mainViewModel.initRetrofit(offset)
        mainViewModel.error.observe(this){
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).setAction("Ok"){}.show()
        }
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
            binding.progressbar.visibility = View.GONE
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
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val array: IntArray? = null
                val firstVisibleItems = layoutManager.findFirstVisibleItemPositions(array)
                var firstVisibleItemPosition = 0
                if(firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                    firstVisibleItemPosition = firstVisibleItems[0];
                }

                if (isScrolling && (firstVisibleItemPosition + visibleItemCount) >= (totalItemCount - 6)
                    || isScrolling && dy < 0
                ){
                    updateData()
                    binding.progressbar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun updateData() {
        isScrolling = false
        offset+=20
        mainViewModel.initRetrofit(offset)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.listOfMovies.removeObservers(this)
        mainViewModel.error.removeObservers(this)
    }

}

