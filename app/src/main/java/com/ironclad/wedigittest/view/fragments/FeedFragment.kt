package com.ironclad.wedigittest.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ironclad.wedigittest.BuildConfig
import com.ironclad.wedigittest.databinding.FragmentFeedBinding
import com.ironclad.wedigittest.utils.Status
import com.ironclad.wedigittest.view.adapters.OnItemClickListener
import com.ironclad.wedigittest.view.adapters.PopularMovieAdapter
import com.ironclad.wedigittest.view.viewmodels.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(), OnItemClickListener {
    private var binding: FragmentFeedBinding? = null
    private val viewModel by viewModels<FeedViewModel>()
    private lateinit var mAdapter: PopularMovieAdapter
    private lateinit var mLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = PopularMovieAdapter(requireContext(), this)
        mLayoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
    }

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

        binding?.recyclerMovies?.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }

        val queries = HashMap<String, Any>()
        queries["api_key"] = BuildConfig.API_KEY
        queries["page"] = 1
        queries["language"] = "en-us"

        viewModel.getPopularMovieList(queries)

        viewModel.popularMovieList.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progress?.visibility = View.GONE
                    binding?.recyclerMovies?.visibility = View.VISIBLE
                    it.data.let { res ->
                        mAdapter.submitList(res?.movieListItems)
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
                        Snackbar.make(rootView, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onItemClick(id: Int) {
        findNavController().navigate(FeedFragmentDirections.goToDetailMovie(id))
    }
}