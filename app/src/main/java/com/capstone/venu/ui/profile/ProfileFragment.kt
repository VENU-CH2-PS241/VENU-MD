package com.capstone.venu.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.capstone.venu.R
import com.capstone.venu.ui.auth.sign_in.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class ProfileFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var tvUsername: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Initialize TextView for displaying username or email
        tvUsername = view.findViewById(R.id.tv_username)

        // Add a listener to the sign-out button
        val btnSignOut: Button = view.findViewById(R.id.btn_sign_out)
        btnSignOut.setOnClickListener {
            logout()
        }

        loadUserInfo()
    }

    private fun loadUserInfo() {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            // User is signed in, update the TextView with the user's email
            val userEmail = currentUser.email
            tvUsername.text = userEmail
        } else {
            // User is not signed in, handle it accordingly
            tvUsername.text = getString(R.string.username)
        }
    }

    private fun logout() {
        // Check if the user signed in with Google
        val currentUser = auth.currentUser
        currentUser?.getIdToken(false)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val signInMethods = task.result?.signInProvider
                if (signInMethods?.contains(GoogleAuthProvider.PROVIDER_ID) == true) {
                    // If the user signed in with Google, sign out from Google
                    val googleSignInClient = GoogleSignIn.getClient(requireContext(), GoogleSignInOptions.DEFAULT_SIGN_IN)
                    googleSignInClient.signOut().addOnCompleteListener {
                        // After signing out from Google, sign out from Firebase
                        signOutFirebase()
                    }
                } else {
                    // If the user didn't sign in with Google, sign out from Firebase directly
                    signOutFirebase()
                }
            }
        }
    }

    private fun signOutFirebase() {
        // Sign out from Firebase
        auth.signOut()

        // Redirect to the sign-in page or the home page of the application
        val intent = Intent(requireContext(), SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}
