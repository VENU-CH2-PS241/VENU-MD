package com.capstone.venu.ui.checker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.venu.R
import com.capstone.venu.data.local.dummy.NewsItem
import com.capstone.venu.databinding.FragmentCheckerResultBinding
import com.capstone.venu.ui.adapter.NewsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckerResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckerResultFragment : Fragment() {
    private lateinit var binding: FragmentCheckerResultBinding
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
        binding = FragmentCheckerResultBinding.inflate(layoutInflater)

        binding.tvResult.text = getString(R.string.lorem_ipsum)
        binding.tvRecommend.text = getString(R.string.recommend)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dummy data
        val dummyData = generateDummyData()

        // RecyclerView and adapter
        val recyclerView: RecyclerView = binding.rvListNews
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NewsAdapter(dummyData)
        recyclerView.adapter = adapter
    }

    private fun generateDummyData(): List<NewsItem> {
        val dummyList = mutableListOf<NewsItem>()

        dummyList.add(NewsItem(R.drawable.img_news, "Sample News Title 1", "80%", "Technology"))
        dummyList.add(NewsItem(R.drawable.img_news, "Sample News Title 2", "65%", "Politics"))
        dummyList.add(NewsItem(R.drawable.img_news, "Sample News Title 3", "90%", "Sports"))

        return dummyList
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CheckerResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CheckerResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}