package com.capstone.venu.ui.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.venu.databinding.ActivitySplashScreenBinding
import com.capstone.venu.ui.auth.sign_in.SignInActivity
import com.capstone.venu.ui.auth.sign_up.SignUpActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Intent(this, SignInActivity::class.java))

        finish()
    }
}