package com.example.testapplicationmovie.model

data class Movie(
    val multimedia: Image,
    val display_title: String = "",
    val summary_short: String = ""
)

data class Image(
    val src: String = ""
)

data class CompanionMovie(
    val results: List<Movie>
)
