package com.example.strongerfootballapp.domain.utils.extensions

import android.graphics.drawable.AdaptiveIconDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.strongerfootballapp.R

fun ImageView.loadImage(url: String?){
    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.soccer)
        .centerCrop()
        .into(this)
}