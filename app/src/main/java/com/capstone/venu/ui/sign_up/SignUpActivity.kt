package com.capstone.venu.ui.sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.venu.databinding.ActivitySignUpBinding
import com.capstone.venu.ui.sign_in.SignInActivity
import com.capstone.venu.utils.ValidateType
import com.capstone.venu.utils.validate

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sign-up button click
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        // "Already have an account?" text click
        binding.tvSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

//        setupAction()
    }

//    nanti
//    private fun setupAction() {
//        binding.apply {
//            btnSignup.setOnClickListener {
//                if (validateForm()) {
//                    val username = username.text.toString()
//                    val email = email.text.toString()
//                    val password = pass.text.toString()
//                    val conPass = conpass.text.toString()
//                    val intent = Intent(this@SignUpActivity,
//                        SignInActivity::class.java)
//                    intent.flags =
//                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                }
//            }
//        }
//    }
//
//    private fun validateForm(): Boolean {
//        binding.apply {
//            val validates = listOf(
//                username.validate("Username", ValidateType.REQUIRED),
//                email.validate("Email", ValidateType.REQUIRED),
//                email.validate("Email", ValidateType.EMAIL),
//                pass.validate("Password", ValidateType.REQUIRED),
//                pass.validate("Password", ValidateType.MIN_CHAR),
//                conpass.validate("Confirm Password", ValidateType.REQUIRED, passwordEditText = conpass),
//                conpass.validate("Confirm Password", ValidateType.CONFIRM_PASSWORD, passwordEditText = conpass),
//            )
//            return !validates.contains(false)
//        }
//    }
}