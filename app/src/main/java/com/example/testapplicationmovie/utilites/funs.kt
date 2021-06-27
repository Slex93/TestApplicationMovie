package com.example.testapplicationmovie.utilites

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testapplicationmovie.R

fun ImageView.downloadAndSet(url: String) {
    Glide.with(this)
        .load(url)
        .optionalCenterCrop()
        .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground))
        .into(this)
}