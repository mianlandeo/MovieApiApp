package com.example.apimovie.navigation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.apimovie.R
import com.example.apimovie.databinding.FragmentDetailsBinding
import com.example.apimovie.model.ApiStatus
import com.example.apimovie.viewmodel.ViewModelDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModelDetails: ViewModelDetails by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelDetails.getDetailsMovie(args.idDetails)
        observeStatus()
        observeDetailsMovies()

    }

    private fun observeStatus() {
        viewModelDetails.loading.observe(viewLifecycleOwner) { status ->
            when (status!!) {
                ApiStatus.LOADING -> {
                    binding.loadingView.visibility = View.VISIBLE
                    binding.appBar.visibility = View.GONE
                    binding.collapsingToolBar.visibility = View.GONE
                    binding.posterGone.visibility = View.GONE
                }

                ApiStatus.DONE -> {
                    binding.loadingView.visibility = View.GONE
                    binding.appBar.visibility = View.VISIBLE
                    binding.collapsingToolBar.visibility = View.VISIBLE
                    binding.posterGone.visibility = View.GONE

                }

                ApiStatus.ERROR -> {
                    binding.loadingView.visibility = View.GONE
                    binding.appBar.visibility = View.GONE
                    binding.collapsingToolBar.visibility = View.GONE
                    binding.posterGone.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observeDetailsMovies() {
        viewModelDetails.movieDetails.observe(viewLifecycleOwner) {

            val image = if (it.backdropPath.isNullOrEmpty()) {
                null
            } else {
                it.backdropPath
            }
            Glide.with(this)
                .load(image)
                .placeholder(
                    R.drawable.loading_animation
                ).into(binding.backdropPathView)

            binding.collapsingToolBar.title = it.title
            binding.tvBudget.text = it.budget
            binding.tvPopularity.text = it.popularity.toString()

            // Sí el resultado de tiempo es nullo ni vacio entonces el resultado es -> - - -
            // Sí el resultado no es nullo ni vacio entonces
            // Texto d% + objeto tiempo le damos el resultado al text Ui
            if (it.runtime.isNullOrEmpty()) {
                getString(R.string.text_empty)
            } else {
                getString(R.string.runtimeLink, it.runtime)
            }.also { resultTime ->
                binding.tvRunTime.text = resultTime
            }
            binding.tvGenders.text = it.genres

            if(it.overview.isNullOrEmpty()){
                binding.tvOverview.visibility = View.GONE
                binding.tvOverviewEmpty.visibility = View.VISIBLE
            } else {
                binding.tvOverview.visibility = View.VISIBLE
                binding.tvOverviewEmpty.visibility = View.GONE
                binding.tvOverview.text = it.overview
            }
        }
    }
}