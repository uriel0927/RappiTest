package com.example.emovie.ui.viewholder

import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.emovie.data.local.bd.model.TopRatedMoviesWithGenres
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.databinding.ItemMovieRecycleviewBinding
import com.example.emovie.resources.Constants

class TopRatedMovieViewHolder(
    private val itemMovieRecycleviewBinding: ItemMovieRecycleviewBinding,
    private val onMovieSelected: (TopRatedMovie) -> Unit
) :
    RecyclerView.ViewHolder(itemMovieRecycleviewBinding.root) {

    fun bind(topRatedMovie: TopRatedMovie) {
        Glide.with(itemMovieRecycleviewBinding.root.context)
            .load("${Constants.SECURE_BASE_URL_IMAGES}${Constants.POSTER_SIZE}/${topRatedMovie.posterPath}")
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(itemMovieRecycleviewBinding.movieIv)

        itemMovieRecycleviewBinding.movieIv.setOnClickListener {
            onMovieSelected(topRatedMovie )
        }


    }

}