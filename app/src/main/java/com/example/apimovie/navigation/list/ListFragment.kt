package com.example.apimovie.navigation.list

import android.R
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.apimovie.databinding.FragmentListBinding
import com.example.apimovie.model.ApiStatus
import com.example.apimovie.viewmodel.ViewModelMovie
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess


@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModelMovie: ViewModelMovie by viewModels()
    private lateinit var adapterMovie: AdapterMovie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterMovie = AdapterMovie()
        viewModelMovie.getPopularMovies()
        observerStatus()
        listPopularMovies()
        pressedCallback()
        onClickMovie()
    }

    private fun observerStatus() {
        viewModelMovie.loading.observe(viewLifecycleOwner) { _status ->
            when (_status!!) {
                ApiStatus.LOADING -> {
                    binding.shimmerLayout.visibility = View.VISIBLE
                    binding.rvMovieLis.visibility = View.GONE
                }

                ApiStatus.ERROR -> {
                    binding.shimmerLayout.visibility = View.VISIBLE
                    binding.rvMovieLis.visibility = View.GONE
                }

                ApiStatus.DONE -> {
                    binding.shimmerLayout.visibility = View.GONE
                    binding.rvMovieLis.visibility = View.VISIBLE
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listPopularMovies() {
        binding.apply {
            rvMovieLis.layoutManager = GridLayoutManager(requireContext(), 2)
            rvMovieLis.adapter = adapterMovie
        }

        viewModelMovie.movies.observe(viewLifecycleOwner) { movies_ ->
            movies_.let {
                adapterMovie.submitList(it)
            }
        }
    }

    private fun onClickMovie() {
        adapterMovie.selectedListener = { movie ->
            //Toast.makeText(requireContext(), movie.id.toString(), Toast.LENGTH_LONG).show()
            Log.e("ERROR_TAG", "2")
            val navigate = ListFragmentDirections.actionListFragmentToDetailsFragment(movie.id)
            findNavController().navigate(navigate)
            Log.e("ERROR_TAG", "3")
        }
    }

    private fun pressedCallback() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(requireContext())
                    .setMessage("¿Salir de la aplicación?")
                    .setCancelable(false)
                    .setPositiveButton("Si") { _, _ ->
                        exitProcess(0)
                    }
                    .setNegativeButton("Cancelar") { _, _ -> }
                    .show()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, onBackPressedCallback
        )
    }
}