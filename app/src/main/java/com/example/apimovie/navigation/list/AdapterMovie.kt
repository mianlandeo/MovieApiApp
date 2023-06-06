package com.example.apimovie.navigation.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apimovie.R
import com.example.apimovie.databinding.ItemMovieBinding
import com.example.apimovie.model.movies.ItemMovie

class AdapterMovie : RecyclerView.Adapter<AdapterMovie.ViewHolder>() {

    var selectedListener: ((ItemMovie) -> Unit)? = null
    private var movie: List<ItemMovie> = mutableListOf()

    class ViewHolder constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding = ItemMovieBinding.bind(view)

        fun bind(item: ItemMovie, itemClickListener: (ItemMovie) -> Unit) {
            binding.apply {
                Glide.with(imageViewMovie)
                    .load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
                    .into(imageViewMovie)
                titleViewMovie.text = item.title
                cvMovie.setOnClickListener {
                    itemClickListener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int = movie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movie[position]
        selectedListener?.let { holder.bind(item, it) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(listMovie: MutableList<ItemMovie>) {
        this.movie = listMovie
        notifyDataSetChanged()
    }
}