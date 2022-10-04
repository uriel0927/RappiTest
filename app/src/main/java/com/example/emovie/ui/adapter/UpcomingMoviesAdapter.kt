package com.example.emovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.data.model.UpcomingMovie
import com.example.emovie.databinding.ItemMovieRecycleviewBinding
import com.example.emovie.ui.viewholder.TopRatedMovieViewHolder
import com.example.emovie.ui.viewholder.UpcomingMovieViewHolder

class UpcomingMoviesAdapter(
    private var upcomingMovies: List<UpcomingMovie>,
    private val onMovieSelected: (topRatedMovie: UpcomingMovie) -> Unit
) :
    RecyclerView.Adapter<UpcomingMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        val binding =
            ItemMovieRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingMovieViewHolder(binding,onMovieSelected)
    }

    override fun onBindViewHolder(holder: UpcomingMovieViewHolder, position: Int) {
        val topRatedMovie = upcomingMovies[position]
        holder.bind(topRatedMovie)
    }

    override fun getItemCount(): Int {
        return upcomingMovies.size
    }

    fun setUpcomingMovies(upcomingMovies: List<UpcomingMovie>) {
        this.upcomingMovies = upcomingMovies
        notifyDataSetChanged()
    }
}