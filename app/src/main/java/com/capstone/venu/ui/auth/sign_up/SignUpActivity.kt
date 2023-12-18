package com.capstone.venu.ui.auth.sign_up

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.capstone.venu.R
import com.capstone.venu.databinding.ActivitySignUpBinding
import com.capstone.venu.ui.auth.sign_in.SignInActivity
import com.capstone.venu.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 1001
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
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


        // Sign-up button click
        binding.btnSignup.setOnClickListener {
            processSignUp()
            validateData()
        }

        binding.btnGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        // "Already have an account?" text click
        binding.tvSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

//        setupAction()
    }

    private fun processSignUp() {

        /*val inputUsername = binding.usernameEdtxt.text.toString()
        val inputEmail = binding.emailEdtxt.text.toString()
        val inputPassword = binding.passEdtxt.text.toString()
        val inputConpass = binding.conpassEdtxt.text.toString()

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
        finish()*/
    }

    private var inputUsername = ""
    private var inputEmail = ""
    private var inputPass = ""
    private fun validateData(){
        inputUsername = binding.usernameEdtxt.text.toString().trim()
        inputEmail = binding.emailEdtxt.text.toString().trim()
        inputPass = binding.passEdtxt.text.toString().trim()
        val inputConpass = binding.conpassEdtxt.text.toString().trim()

        if (inputUsername.isEmpty()){
            Toast.makeText(this, "Enter your username...", Toast.LENGTH_SHORT).show()
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){
            Toast.makeText(this, "Invalid Email Pattter...", Toast.LENGTH_SHORT).show()
        }
        else if (inputPass.isEmpty()){
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        }
        else if (inputConpass.isEmpty()){
            Toast.makeText(this, "Confirm password...", Toast. LENGTH_SHORT).show()
        }
        else if (inputPass != inputConpass){
            Toast.makeText(this, "Password doesn't match...", Toast.LENGTH_SHORT).show()
        }
        else {
            createUserAccount()
        }
    }

    private fun createUserAccount(){
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        auth.createUserWithEmailAndPassword(inputEmail, inputPass)
            .addOnSuccessListener {
                updateUserInfo()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed creating account due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUserInfo(){

        progressDialog.setMessage("Saving user info...")

        val timestamp = System.currentTimeMillis()

        val uid = auth.uid

        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["username"] = inputUsername
        hashMap["email"] = inputEmail
        hashMap["timestamp"] = timestamp

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Account created...", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed saving user info due to ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        // Tambahkan finish() jika ingin menutup Activity SignIn setelah pindah ke MainActivity
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
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }

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