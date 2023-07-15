package com.gabniel.elibrary.screen.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gabniel.elibrary.databinding.ActivitySplashscreenBinding
import com.gabniel.elibrary.screen.auth.login.LoginActivity
import com.gabniel.elibrary.screen.main.MainActivity
import com.gabniel.elibrary.utils.AppManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashscreenActivity : AppCompatActivity() {

    @Inject
    lateinit var appManager: AppManager

    lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadSession()
    }

    private fun loadSession() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (appManager.isLogin() != "") {
                startActivity(Intent(this, MainActivity::class.java))
            } else startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}