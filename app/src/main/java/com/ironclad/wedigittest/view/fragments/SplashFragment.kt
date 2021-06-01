package com.ironclad.wedigittest.view.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ironclad.wedigittest.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var binding: FragmentSplashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(SplashFragmentDirections.goToFeed())
        }, SPLASH_DELAY)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val SPLASH_DELAY: Long = 3000
    }
}