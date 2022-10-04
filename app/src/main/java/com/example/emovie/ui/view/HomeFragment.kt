package com.example.emovie.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emovie.R
import com.example.emovie.data.model.TopRatedMovie
import com.example.emovie.data.model.Translations
import com.example.emovie.data.model.UpcomingMovie
import com.example.emovie.databinding.FragmentHomeBinding
import com.example.emovie.ui.UiStateHomeFragment
import com.example.emovie.ui.adapter.TopRatedMoviesAdapter
import com.example.emovie.ui.adapter.UpcomingMoviesAdapter
import com.example.emovie.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding

    private val binding get() : FragmentHomeBinding = _binding

    private lateinit var adapterTopRatedMovies: TopRatedMoviesAdapter
    private lateinit var adapterRecomendedMovies: TopRatedMoviesAdapter
    private lateinit var adapterUpcomingMovies: UpcomingMoviesAdapter
    private lateinit var adapterYears : ArrayAdapter<String>
    private lateinit var adapterTranslations : ArrayAdapter<String>

    private val viewmodel by viewModels<HomeViewModel>()

    private lateinit var navController: NavController

    private var yearSelected = ""
    private var translationSelected = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel.uistate.observe(this, ::observeUiStateFragment)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        initTopRatedRecycleView()
        initUpcomingRecycleView()
        initRecomendedForYouRecycleView()

        viewmodel.getToRatedMovies()
        viewmodel.getUpcomingMovies()
        viewmodel.getRecomendedForYou()
        viewmodel.getConfigurations()

        initFilters()

        binding.filtersYears.setOnItemClickListener { adapterView, view, i, l ->
            yearSelected = adapterYears.getItem(i) ?: ""
            viewmodel.getRecomendedForYou(language = translationSelected.split("-")[0] , releaseYear = yearSelected )
        }

        binding.filtersLanguages.setOnItemClickListener { adapterView, view, i, l ->
            translationSelected = adapterTranslations.getItem(i) ?: ""
            viewmodel.getRecomendedForYou(language = translationSelected.split("-")[0] , releaseYear = yearSelected )
        }
    }

    private fun initFilters() {
        adapterYears = ArrayAdapter(requireContext(),R.layout.item_filter, listOf())
        binding.filtersYears.setAdapter(adapterYears)

        adapterTranslations = ArrayAdapter(requireContext(),R.layout.item_filter, listOf())
        binding.filtersLanguages.setAdapter(adapterTranslations)
    }

    private fun initRecomendedForYouRecycleView() {
        val linearLayoutManager = GridLayoutManager(requireContext(),3)


        adapterRecomendedMovies = TopRatedMoviesAdapter(
            topRatedMovies = listOf(),
            onMovieSelected = { onMovieSelected ->

                onTopRatedMovieSelected(onMovieSelected)

            })
        binding.recyclerViewRecomended.adapter = adapterRecomendedMovies
        binding.recyclerViewRecomended.layoutManager = linearLayoutManager
    }

    private fun initTopRatedRecycleView() {

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        adapterTopRatedMovies = TopRatedMoviesAdapter(
            topRatedMovies = listOf(),
            onMovieSelected = { onMovieSelected ->

                onTopRatedMovieSelected(onMovieSelected)

            })
        binding.recyclerViewTopRated.adapter = adapterTopRatedMovies
        binding.recyclerViewTopRated.layoutManager = linearLayoutManager

    }

    private fun initUpcomingRecycleView() {

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        adapterUpcomingMovies = UpcomingMoviesAdapter(
            upcomingMovies = listOf(),
            onMovieSelected = {
                onUpcomingMovieSelected(it)
            })
        binding.recyclerViewUpComing.adapter = adapterUpcomingMovies
        binding.recyclerViewUpComing.layoutManager = linearLayoutManager

    }

    private fun observeUiStateFragment(uiState: UiStateHomeFragment) {

        when (uiState) {
            UiStateHomeFragment.ErrorLoadedData -> {
                binding.configurationsLoading.isVisible = false
            }
            UiStateHomeFragment.Loading -> Unit
            is UiStateHomeFragment.TopRatedMoviesLoaded -> {
                refreshTopRatedRecycleView(uiState.topRatedMovies)
            }
            is UiStateHomeFragment.UpcomingMoviesLoaded -> {
                refreshUpcomingRecycleView(uiState.upcomingMovies)
            }
            is UiStateHomeFragment.ReocomndedForYouLoaded ->
                refreshRecomendedForYouRecycleView(uiState.data)
            is UiStateHomeFragment.ConfigurationsLoaded -> {
                binding.configurationsLoading.isVisible = false
                loadFilterYears(uiState.configurations.yearsRelease)
                loadFilterLanguages(uiState.configurations.translations)
            }
            UiStateHomeFragment.LoadingConfigurations -> {
                showLoadingFilters()
            }
        }

    }

    private fun showLoadingFilters() {
        binding.configurationsLoading.isVisible = true
    }

    private fun loadFilterYears(yearsRelease: List<String>) {
        adapterYears = ArrayAdapter(requireContext(),R.layout.item_filter,yearsRelease)
        binding.filtersYears.setAdapter(adapterYears)
    }

    private fun loadFilterLanguages(translations: List<Translations>) {
        val transaltionsString = translations.map { it.toStringTranslations() }
        adapterTranslations = ArrayAdapter(requireContext(),R.layout.item_filter,transaltionsString)
        binding.filtersLanguages.setAdapter(adapterTranslations)
    }

    private fun Translations.toStringTranslations() : String{
        return "${this.languagesIso}-${this.isoCountry}(${this.nameCountry})"
    }


    private fun onTopRatedMovieSelected(
        topRatedMovie: TopRatedMovie
    ) {

        val extras = Bundle()

        extras.putParcelable(  "topRatedMovie", topRatedMovie)
        extras.putParcelable(  "upcomingMovie", null)

        navController.navigate(R.id.action_homeFragment_to_detailMovieFragment, extras)
    }

    private fun onUpcomingMovieSelected(upcomingMovie: UpcomingMovie) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(upcomingMovie = upcomingMovie)
        navController.navigate(action)
    }

    private fun refreshTopRatedRecycleView(topRatedMovies: List<TopRatedMovie>) {
        adapterTopRatedMovies.setTopRatedMovies(topRatedMovies)
    }

    private fun refreshUpcomingRecycleView(upcomingMovies: List<UpcomingMovie>) {
        adapterUpcomingMovies.setUpcomingMovies(upcomingMovies)
    }


    private fun refreshRecomendedForYouRecycleView(recomendedMovies: List<TopRatedMovie>) {
        adapterRecomendedMovies.setTopRatedMovies(recomendedMovies)
    }

}