package com.example.emovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.databinding.ItemMovieRecycleviewBinding
import com.example.emovie.ui.viewholder.TopRatedMovieViewHolder

class TopRatedMoviesAdapter(
    private var topRatedMovies: List<TopRatedMovie>,
    private val onMovieSelected: (topRatedMovie: TopRatedMovie) -> Unit
) :
    RecyclerView.Adapter<TopRatedMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMovieViewHolder {
        val binding =
            ItemMovieRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopRatedMovieViewHolder(binding,onMovieSelected)
    }

    override fun onBindViewHolder(holder: TopRatedMovieViewHolder, position: Int) {
        val topRatedMovie = topRatedMovies[position]
        holder.bind(topRatedMovie)
    }

    override fun getItemCount(): Int {
        return topRatedMovies.size
    }

    fun setTopRatedMovies(topRatedMovies: List<TopRatedMovie>) {
        this.topRatedMovies = topRatedMovies
        notifyDataSetChanged()
    }
}