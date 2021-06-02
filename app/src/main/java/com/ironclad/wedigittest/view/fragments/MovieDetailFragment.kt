package com.ironclad.wedigittest.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.ironclad.wedigittest.BuildConfig
import com.ironclad.wedigittest.data.model.Movie
import com.ironclad.wedigittest.databinding.FragmentDetailMovieBinding
import com.ironclad.wedigittest.utils.Status
import com.ironclad.wedigittest.utils.convertBudget
import com.ironclad.wedigittest.utils.convertDate
import com.ironclad.wedigittest.utils.convertRuntime
import com.ironclad.wedigittest.view.adapters.CastAdapter
import com.ironclad.wedigittest.view.adapters.CompanyAdapter
import com.ironclad.wedigittest.view.adapters.CrewAdapter
import com.ironclad.wedigittest.view.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var binding: FragmentDetailMovieBinding? = null
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel by viewModels<MovieDetailViewModel>()
    private lateinit var mAdapter: CompanyAdapter
    private lateinit var mCastAdapter: CastAdapter
    private lateinit var mCrewAdapter: CrewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = CompanyAdapter(requireContext())
        mCastAdapter = CastAdapter(requireContext())
        mCrewAdapter = CrewAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)

        val queries = HashMap<String, Any>()
        queries["api_key"] = BuildConfig.API_KEY
        queries["language"] = "en-US"

        viewModel.getMovieDetails(args.movieId, queries)
        viewModel.getCreditList(args.movieId, queries)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvPC?.apply {
            adapter = mAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding?.rvCast?.apply {
            adapter = mCastAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding?.rvCrew?.apply {
            adapter = mCrewAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.movieDetails.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progress?.visibility = View.GONE
                    binding?.content?.visibility = View.VISIBLE
                    inflateValues(it.data)
                }
                Status.LOADING -> {
                    binding?.progress?.visibility = View.VISIBLE
                    binding?.content?.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding?.progress?.visibility = View.GONE
                    binding?.content?.visibility = View.GONE
                    binding?.apply {
                        Snackbar.make(rootView, "Something went wrong", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })

        viewModel.creditList.observe(requireActivity(), {
            when (it.status) {
                Status.SUCCESS -> {
                    binding?.progress?.visibility = View.GONE
                    binding?.content?.visibility = View.VISIBLE
                    it.data.let { res ->
                        mCastAdapter.submitList(res?.cast)
                        mCrewAdapter.submitList(res?.crew)
                    }
                }
                Status.LOADING -> {
                    binding?.progress?.visibility = View.VISIBLE
                    binding?.content?.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding?.progress?.visibility = View.GONE
                    binding?.content?.visibility = View.GONE
                    binding?.apply {
                        Snackbar.make(rootView, "Something went wrong", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun inflateValues(movie: Movie?) {
        binding?.apply {
            tvTitle.text = movie?.title
            tvYear.text = movie?.releaseDate?.substring(0, 4)
            tvGenres.text = if (movie?.genres?.size!! >= 2) {
                movie.genres?.get(0)?.name + ", " + movie.genres?.get(1)?.name
            } else {
                movie.genres?.get(0)?.name
            }
            Glide.with(this@MovieDetailFragment)
                .load("${BuildConfig.IMAGE_BASE_URL}${movie.backdropPath}")
                .centerCrop()
                .into(imageBackdrop)
            tvBudget.text = convertBudget(movie.budget)
            tvRuntime.text = convertRuntime(movie.runtime)
            tvOverview.text = movie.overview
            tvTagLine.text = movie.tagline
            tvRelease.text = convertDate(movie.releaseDate)
            tvRuntimeDetails.text = "${movie.runtime} mins"
            mAdapter.submitList(movie.productionCompanies)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}