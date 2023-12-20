package com.capstone.venu.ui.checker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.capstone.venu.R
import com.capstone.venu.databinding.EmptyCheckerLayoutBinding
import com.capstone.venu.databinding.FragmentCheckerBinding
import com.capstone.venu.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckerFragment : Fragment() {
    private lateinit var binding: EmptyCheckerLayoutBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmptyCheckerLayoutBinding.inflate(layoutInflater)

        binding.ivChecker.setImageResource(R.drawable.img_checker)
        binding.tvCheckerMessage.text = getString(R.string.checker_message)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FAB click
        val fab: FloatingActionButton = binding.btnCheck
        fab.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_checker, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        val analyzeButton: Button = bottomSheetView.findViewById(R.id.btn_analyze)

        analyzeButton.setOnClickListener {
            val fragmentChecker = CheckerResultFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragmentChecker)
            transaction.addToBackStack(null)
            transaction.commit()

            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CheckerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CheckerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}