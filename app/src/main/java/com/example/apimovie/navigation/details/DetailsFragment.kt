package com.example.apimovie.navigation.details

import android.os.Bundle
import android.util.Log
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
    val args : DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("ERROR_TAG", "1")
        Log.e("ERROR_TAG", "${args.idDetails}")
        observeStatus()
        observeDetailsMovies()
        viewModelDetails.getDetailsMovie(args.idDetails)

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
            //Muestra Datos si el resultado no es vacio
            val image = it.backdropPath.ifEmpty {
                it.posterPath
            }
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/${image}")
                .placeholder(
                    R.drawable.loading_animation
                ).into(binding.posterView)
            if (it.overview.isEmpty()) {
                binding.tvOverviewEmpty.visibility = View.VISIBLE
                binding.tvOverview.visibility = View.GONE
            } else {
                binding.tvOverviewEmpty.visibility = View.GONE
                binding.tvOverview.visibility = View.VISIBLE
                binding.tvOverview.text = it.overview
            }
            binding.collapsingToolBar.title = it.title
            binding.tvBudget.text = it.budget
            binding.tvPopularity.text = it.popularity.toString()
            binding.tvReleaseData.text = it.releaseDate
            (if (it.runtime.isEmpty()) {
                getString(R.string.text_empty)
            } else {
                getString(R.string.runtimeLink, it.runtime)
            }).also { time ->
                binding.tvRuntime.text = time
            }
            Log.d("superTag", "${it}")
        }
    }

    companion object {
        var idMovie = 1
    }
}