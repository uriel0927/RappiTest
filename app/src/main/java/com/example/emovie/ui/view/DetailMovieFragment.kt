package com.example.emovie.ui.view

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.emovie.R
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.data.model.UpcomingMovie
import com.example.emovie.databinding.FragmentDetailMovieBinding
import com.example.emovie.resources.Constants
import com.example.emovie.ui.UiStateDetailMovieFragment
import com.example.emovie.ui.viewmodel.DetailMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private lateinit var _binding: FragmentDetailMovieBinding

    private val binding get() = _binding

    private lateinit var navController: NavController

    private val navargs by navArgs<DetailMovieFragmentArgs>()

    private var topRatedMovie: TopRatedMovie? = null
    private var upComingMovie: UpcomingMovie? = null

    private val viewmodel: DetailMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topRatedMovie = navargs.topRatedMovie
        upComingMovie = navargs.upcomingMovie

        viewmodel.uistate.observe(this, ::observeUIstate)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailMovieBinding.inflate(inflater)

        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation

        binding.toolbar2.setNavigationOnClickListener {

            navController.popBackStack()
        }

        binding.button.setOnClickListener {

            val idMovie = if (topRatedMovie != null) {
                topRatedMovie!!.id
            } else if (upComingMovie != null) {
                upComingMovie!!.id
            } else {
                0
            }

            viewmodel.getVideoData(idMovie)

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)



        if (topRatedMovie != null) {

            binding.titleMovie.text = topRatedMovie!!.tittle
            binding.languageTv.text = topRatedMovie!!.language
            binding.voteAvergaeTv.text = "${topRatedMovie!!.voteAverage}"
            binding.yearReleaseTv.text = extractYear(topRatedMovie!!.releaseDate)
            binding.overviewTv.text = topRatedMovie!!.overview

            Glide.with(requireContext())
                .load("${Constants.SECURE_BASE_URL_IMAGES}${Constants.POSTER_SIZE_500}/${navargs.topRatedMovie!!.posterPath}")
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.movieIv)

        } else if (upComingMovie != null) {

            binding.titleMovie.text = upComingMovie!!.tittle
            binding.languageTv.text = upComingMovie!!.language
            binding.voteAvergaeTv.text = "${upComingMovie!!.voteAverage}"
            binding.yearReleaseTv.text = extractYear(upComingMovie!!.releaseDate)
            binding.overviewTv.text = upComingMovie!!.overview

            Glide.with(requireContext())
                .load("${Constants.SECURE_BASE_URL_IMAGES}${Constants.POSTER_SIZE_500}/${navargs.upcomingMovie!!.posterPath}")
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.movieIv)

        }


    }

    private fun extractYear(releaseDate: String): String {

        val splitDate = releaseDate.split("-")

        return splitDate[0]

    }

    fun observeUIstate(uistate: UiStateDetailMovieFragment) {

        when (uistate) {
            UiStateDetailMovieFragment.ErrorDataVideo -> {
                Toast.makeText(requireContext(), R.string.error_data_video,Toast.LENGTH_LONG).show()
            }

            UiStateDetailMovieFragment.Loading -> Unit
            is UiStateDetailMovieFragment.VideoDataLoaded -> {
                val intent = Intent(requireActivity(),ShowVideoActivity::class.java)
                intent.putExtra(Constants.EXTRA_KEY_YOUTUBE_VIDEO, uistate.data.key)
                startActivity(intent)

            }
        }

    }


}