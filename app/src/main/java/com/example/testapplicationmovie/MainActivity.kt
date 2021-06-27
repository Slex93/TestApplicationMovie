package com.example.testapplicationmovie

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.testapplicationmovie.adapter.MainAdapter
import com.example.testapplicationmovie.databinding.ActivityMainBinding
import com.example.testapplicationmovie.model.RetrofitRepository
import com.example.testapplicationmovie.viewmodel.MainViewModel
import com.example.testapplicationmovie.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter

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
        mainViewModel.initRetrofit()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = binding.recyclerView
        adapter = MainAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mainViewModel.listOfMovies.observe(this){
            it.forEach {
                Log.i("result::", it.toString())
                adapter.addMovie(it)
            }
        }
    }

}