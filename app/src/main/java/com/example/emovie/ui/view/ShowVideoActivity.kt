package com.example.emovie.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.emovie.R
import com.example.emovie.databinding.ActivityShowVideoBinding
import com.example.emovie.resources.Constants
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class ShowVideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var _binding: ActivityShowVideoBinding
    private val binding get() = _binding

    private var idMovie: Int = 0
    private var keyYoutubeVideo = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityShowVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        keyYoutubeVideo = intent.extras?.getString(Constants.EXTRA_KEY_YOUTUBE_VIDEO) ?: ""
        initYoutubePlayer(keyYoutubeVideo)


    }


    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        youtubePLayer: YouTubePlayer?,
        restaurado: Boolean
    ) {
        if (!restaurado) {
            youtubePLayer?.loadVideo(keyYoutubeVideo)
        }
    }



    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult
    ) {

        if(youTubeInitializationResult.isUserRecoverableError){
            youTubeInitializationResult.getErrorDialog(this,1).show()
        }else{
            val error = "Error al inicializar youtube ${youTubeInitializationResult.toString()}"
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        }
    }

    private fun initYoutubePlayer(key : String) {
        keyYoutubeVideo = key
        binding.youtubeView.initialize(Constants.API_KEY_YOUTUBE, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 1){
            getYoutubePlayerProvider().initialize(Constants.API_KEY_YOUTUBE,this)
        }
    }

    private fun getYoutubePlayerProvider() : YouTubePlayer.Provider{
        return binding.youtubeView
    }
}