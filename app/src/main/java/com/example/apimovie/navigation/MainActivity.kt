package com.example.apimovie.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apimovie.R
import com.example.apimovie.databinding.ActivityMainBinding
import com.example.apimovie.domain.SelectedListener
import com.example.apimovie.navigation.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SelectedListener  {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onClickListener(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)

        val detailsMovies = DetailsFragment()
        detailsMovies.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, detailsMovies)
            .addToBackStack(null)
            .commit()
    }

}