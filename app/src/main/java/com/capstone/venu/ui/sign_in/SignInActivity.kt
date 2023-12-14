package com.capstone.venu.ui.sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.venu.R
import com.capstone.venu.databinding.ActivitySignInBinding
import com.capstone.venu.ui.main.MainActivity
import com.capstone.venu.ui.sign_up.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sign-in button click
        binding.btnSignin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // "Don't have an account?" text click
        binding.tvSigninBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}