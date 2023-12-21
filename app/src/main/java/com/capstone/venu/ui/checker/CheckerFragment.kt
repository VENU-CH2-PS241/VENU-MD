package com.capstone.venu.ui.checker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.capstone.venu.R
import com.capstone.venu.databinding.FragmentCheckerBinding
import com.capstone.venu.ui.CheckerResultActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CheckerFragment : Fragment() {
    private lateinit var binding: FragmentCheckerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckerBinding.inflate(layoutInflater)

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
            // Ganti ini agar pindah ke CheckerResultActivity
            startActivity(Intent(requireContext(), CheckerResultActivity::class.java))
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

}