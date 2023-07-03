package com.example.swiggycloneapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.swiggycloneapp.databinding.SplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity: AppCompatActivity() {

    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        val videoPath = "android.resource://${packageName}/${R.raw.swiggy_splashscreen}"
        val videoUri = Uri.parse(videoPath)
        binding.videoView.setVideoURI(videoUri)

        binding.videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.start()
        }
        binding.videoView.setOnCompletionListener {
            startActivity(Intent(this,MainActivity::class.java))
            overridePendingTransition(R.anim.slide_up, 0)
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.videoView.stopPlayback()
    }

}