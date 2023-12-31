import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.capstone.venu.R
import com.capstone.venu.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.capstone.venu.ui.about.AboutActivity
import com.capstone.venu.ui.advertisement.AdvertisementActivity
import com.capstone.venu.ui.auth.sign_in.SignInActivity
import com.capstone.venu.ui.bookmark.BookmarkActivity
import com.capstone.venu.ui.detail_profile.DetailProfileActivity
import com.capstone.venu.ui.faq.FaqActivity
import com.capstone.venu.ui.feedback.FeedbackActivity

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        val cvUsername: CardView = binding.cardView
        val btnBookmark: Button = binding.btnBookmark
        val btnSubscribe: Button = binding.btnSubscribe
        val btnFeedback: Button = binding.btnFeedback
        val btnFaq: Button = binding.btnFaq
        val btnAbout: Button = binding.btnAbout
        val btnSignout: Button = binding.btnSignOut

        cvUsername.setOnClickListener {
            val intent = Intent(requireContext(), DetailProfileActivity::class.java)
            startActivity(intent)
        }

        btnBookmark.setOnClickListener {
            val intent = Intent(requireContext(), BookmarkActivity::class.java)
            startActivity(intent)
        }

        btnSubscribe.setOnClickListener {
            val intent = Intent(requireContext(), AdvertisementActivity::class.java)
            startActivity(intent)
        }

        btnFeedback.setOnClickListener {
            val intent = Intent(requireContext(), FeedbackActivity::class.java)
            startActivity(intent)
        }

        btnFaq.setOnClickListener {
            val intent = Intent(requireContext(), FaqActivity::class.java)
            startActivity(intent)
        }

        btnAbout.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity::class.java)
            startActivity(intent)
        }

        btnSignout.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        loadUserInfo()
    }

    private fun loadUserInfo() {
        val currentUser = auth.currentUser

        val tvUsername: TextView = binding.tvUsername

        if (currentUser != null) {
            // User is signed in, update the TextView with the user's email
            val userEmail = currentUser.email
            tvUsername.text = userEmail
        } else {
            // User is not signed in, handle it accordingly
            tvUsername.text = getString(R.string.username)
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.logout_confirmation_title))
            .setPositiveButton(getString(R.string.logout_confirmation_yes)) { dialog, _ ->
                dialog.dismiss()
                logout()
            }
            .setNegativeButton(getString(R.string.logout_confirmation_no)) { dialog, _ ->
                dialog.dismiss()
            }

        val alertDialog = builder.create()
        alertDialog.show()
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
