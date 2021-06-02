package com.ironclad.wedigittest.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.ironclad.wedigittest.BuildConfig
import com.ironclad.wedigittest.databinding.FragmentFeedBinding
import com.ironclad.wedigittest.utils.Status
import com.ironclad.wedigittest.view.viewmodels.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private var binding: FragmentFeedBinding? = null
    private val viewModel by viewModels<FeedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val queries = HashMap<String, Any>()
        queries["api_key"] = apiKey
        queries["page"] = 1
        queries["language"] = "en-us"

        viewModel.getPopularMovieList(queries)

        viewModel.popularMovieList.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progress?.visibility = View.GONE
                    binding?.recyclerMovies?.visibility = View.VISIBLE
                    it.data.let { res ->
                        Log.d(
                            "PUI3", """
                            Current Page: ${res?.page}
                            Total Page: ${res?.totalPages}
                        """.trimIndent()
                        )
                    }
                }
                Status.LOADING -> {
                    binding?.apply {
                        progress.visibility = View.VISIBLE
                        recyclerMovies.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    binding?.apply {
                        progress.visibility = View.GONE
                        recyclerMovies.visibility = View.VISIBLE
                        Snackbar.make(rootView, "Somethig went wrong", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val apiKey = BuildConfig.API_KEY
    }
}