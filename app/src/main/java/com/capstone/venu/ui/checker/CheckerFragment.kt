package com.capstone.venu.ui.checker

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.capstone.venu.R
import com.capstone.venu.customview.ClearableEditText
import com.capstone.venu.data.api.ml.ApiConfigML
import com.capstone.venu.data.api.ml.ApiServiceML
import com.capstone.venu.databinding.FragmentCheckerBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CheckerFragment : Fragment() {
    private lateinit var binding: FragmentCheckerBinding
    private lateinit var apiServiceML: ApiServiceML

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckerBinding.inflate(layoutInflater)
        apiServiceML = ApiConfigML.getApiML()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab: FloatingActionButton = binding.btnCheck
        fab.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_checker, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        val inputEdtx: ClearableEditText = bottomSheetView.findViewById(R.id.input_edtx)
        val btnAnalyze: AppCompatButton = bottomSheetView.findViewById(R.id.btn_analyze)

        btnAnalyze.setOnClickListener {
            val userInput: Editable? = inputEdtx.text
            userInput?.let {
                navigateToCheckerResult(it.toString())
                bottomSheetDialog.dismiss()
            }
        }

        bottomSheetDialog.show()
    }

    private fun navigateToCheckerResult(checkerText: String) {
        val intent = CheckerResultActivity.newIntent(requireContext(), checkerText, "N/A")
        startActivity(intent)
    }
}
