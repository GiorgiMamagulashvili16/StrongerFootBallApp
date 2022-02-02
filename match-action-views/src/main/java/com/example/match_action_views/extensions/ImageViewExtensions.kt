package com.example.match_action_views.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.match_action_views.R


fun ImageView.loadImage(url: String?){
    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.football_player)
        .centerCrop()
        .into(this)
}