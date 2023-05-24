package com.example.apimovie.navigation.splash

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.apimovie.R
import com.example.apimovie.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationView()
        finishFragment()
    }

    private fun finishFragment() {
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_listFragment)
        }, TIME_FINISH_ANIMATION)
    }

    // Lottie
    private fun animationView() {
        val textAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_top)
        binding.apply {
            animationView.setAnimation(R.raw.popcorn)
            animationView.repeatCount = ValueAnimator.INFINITE
            animationView.playAnimation()
            animationView.animate()
                .alpha(1f)
                .setDuration(100)
                .start()
            binding.titleSplash.visibility = View.VISIBLE
            binding.titleSplash.startAnimation(textAnim)
        }
    }

    companion object {
        const val TIME_FINISH_ANIMATION = 3000L
    }


}