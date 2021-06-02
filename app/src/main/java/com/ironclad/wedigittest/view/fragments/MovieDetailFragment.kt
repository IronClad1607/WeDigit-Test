package com.ironclad.wedigittest.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ironclad.wedigittest.databinding.FragmentDetailMovieBinding
import com.ironclad.wedigittest.view.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var binding: FragmentDetailMovieBinding? = null
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}