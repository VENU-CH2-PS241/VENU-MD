package com.capstone.venu.ui.auth.sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.capstone.venu.databinding.ActivitySignUpBinding
import com.capstone.venu.ui.auth.sign_in.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)

        // Sign-up button click
        binding.btnSignup.setOnClickListener {
            processSignUp()
        }

        // "Already have an account?" text click
        binding.tvSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

//        setupAction()
    }

    private fun processSignUp() {

        val inputUsername = binding.username.text.toString()
        val inputEmail = binding.email.text.toString()
        val inputPassword = binding.pass.text.toString()
        val inputConpass = binding.conpass.text.toString()

        if (confirmPassword(inputPassword, inputConpass)) {
            auth = FirebaseAuth.getInstance() // Inisialisasi objek auth jika belum dilakukan sebelumnya

            auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userUpdateProfile = userProfileChangeRequest {
                            displayName = inputUsername
                        }
                        val user = auth.currentUser
                        user?.updateProfile(userUpdateProfile)
                            ?.addOnCompleteListener {
                                navigateToSignInActivity()
                            }
                            ?.addOnFailureListener { error2 ->
                                Toast.makeText(this, error2.localizedMessage, LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener { error ->
                    Toast.makeText(this, error.localizedMessage, LENGTH_SHORT).show()
                }
        } else {
            // Password tidak cocok, berikan pesan kesalahan atau lakukan tindakan yang sesuai
            Toast.makeText(this, "Password and Confirm Password do not match", LENGTH_SHORT).show()
        }
    }

    private fun confirmPassword(password: String, confirmPassword: String): Boolean {
        // Fungsi untuk memeriksa apakah password dan confirm password cocok
        return password == confirmPassword
    }

    private fun navigateToSignInActivity() {
        startActivity(Intent(this, SignInActivity::class.java))
        // Tambahkan finish() jika ingin menutup Activity SignUp setelah pindah ke SignInActivity
        finish()
    }


    //    nanti
    /*private fun setupAction() {
        binding.apply {
            btnSignup.setOnClickListener {
                if (validateForm()) {
                    val username = username.text.toString()
                    val email = email.text.toString()
                    val password = pass.text.toString()
                    val conPass = conpass.text.toString()
                    val intent = Intent(
                        this@SignUpActivity,
                        SignInActivity::class.java
                    )
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        binding.apply {
            val validates = listOf(
                username.validate("Username", ValidateType.REQUIRED),
                email.validate("Email", ValidateType.REQUIRED),
                email.validate("Email", ValidateType.EMAIL),
                pass.validate("Password", ValidateType.REQUIRED),
                pass.validate("Password", ValidateType.MIN_CHAR),
                conpass.validate(
                    "Confirm Password",
                    ValidateType.REQUIRED,
                    passwordEditText = conpass
                ),
                conpass.validate(
                    "Confirm Password",
                    ValidateType.CONFIRM_PASSWORD,
                    passwordEditText = conpass
                ),
            )
            return !validates.contains(false)
        }
    }*/
}