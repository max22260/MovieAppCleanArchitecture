package com.nagy.movieapp.findmovie.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nagy.movieapp.common.data.api.ApiConstants.LOAD_IMAGES_ENDPOINT
import com.nagy.movieapp.common.domain.model.movie.MovieDetails
import com.nagy.movieapp.common.utils.setImage
import com.nagy.movieapp.databinding.RecyclerViewMovieItemBinding


class MoviesAdapter : ListAdapter<MovieDetails, MoviesAdapter.MoviesViewHolder>(ITEM_COMPARATOR) {


    private var movieClickListener: MovieClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            RecyclerViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item: MovieDetails = getItem(position)

        holder.bind(item)
    }

    inner class MoviesViewHolder(
        private val binding: RecyclerViewMovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDetails) {

            with(binding) {
                textView.text = item.originalTitle
                imageView.setImage(LOAD_IMAGES_ENDPOINT + item.posterPath)
                root.setOnClickListener {
                    movieClickListener?.onClick(item)
                }
            }
        }
    }

    fun setMovieClickListener(movieClickListener: MovieClickListener) {
        this.movieClickListener = movieClickListener
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<MovieDetails>() {
    override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return oldItem == newItem
    }
}

interface MovieClickListener {
    fun onClick(movieDetails: MovieDetails)
}