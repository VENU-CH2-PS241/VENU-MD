package com.capstone.venu.ui.auth.sign_in

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.capstone.venu.R
import com.capstone.venu.databinding.ActivitySignInBinding
import com.capstone.venu.ui.auth.sign_up.SignUpActivity
import com.capstone.venu.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.security.AuthProvider

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 1001
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firebase auth
        auth = FirebaseAuth.getInstance()

        //progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Sign-in button click
        binding.btnSignin.setOnClickListener {
            validateData()
        }

        binding.btnGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        // "Don't have an account?" text click
        binding.tvSigninBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private var inputEmail = ""
    private var inputPass = ""
    private fun validateData() {

        inputEmail = binding.emailEdtxt.text.toString().trim()
        inputPass = binding.passEdtxt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){
            Toast.makeText(this, "Invalid Email Pattter...", Toast.LENGTH_SHORT).show()
        }
        else if (inputPass.isEmpty()){
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        }
        else {
            loginUser()
        }

    }

    private fun loginUser() {
        progressDialog.setMessage("Sign In Account...")
        progressDialog.show()

        auth.signInWithEmailAndPassword(inputEmail, inputPass)
            .addOnSuccessListener {
                checkUser()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Sign failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        progressDialog.setMessage("Checking User...")

        val firebaseUser = auth.currentUser

        if (firebaseUser != null) {
            val uid = firebaseUser.uid

            val ref = FirebaseDatabase.getInstance().getReference("Users").child(uid)

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    progressDialog.dismiss()

                    if (dataSnapshot.exists()) {
                        // Pengguna terdaftar, pindahkan ke MainActivity
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                        finish()
                    } else {
                        // Pengguna belum terdaftar, berikan pesan atau ambil tindakan yang sesuai
                        Toast.makeText(this@SignInActivity, "User not registered", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    progressDialog.dismiss()
                    Toast.makeText(this@SignInActivity, "Failed to check user due to ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            progressDialog.dismiss()
            Toast.makeText(this@SignInActivity, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun processSignIn() {

        val inputEmail = binding.emailEdtxt.text.toString()
        val inputPassword = binding.passEdtxt.text.toString()

        auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign-in berhasil, navigasikan ke MainActivity
                    navigateToMainActivity()
                } else {
                    Toast.makeText(
                        this,
                        "Sign-in failed. Please check your credentials.",
                        LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.localizedMessage, LENGTH_SHORT).show()
            }
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
//            menangani proses sign in google
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
//                jika berhasil
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                e.printStackTrace()
                Toast.makeText(applicationContext, e.localizedMessage, LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credentian = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credentian)
            .addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.localizedMessage, LENGTH_SHORT).show()
            }

    }

}