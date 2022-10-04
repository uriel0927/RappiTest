package com.example.emovie.ui.view

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.emovie.R
import com.example.emovie.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch{
            delay(2000)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
            overridePendingTransition(R.anim.slide_in_rigth_transition,R.anim.slide_out_left_transition)
            finish()
        }


    }
}