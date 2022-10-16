package com.nagy.movieapp.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.nagy.movieapp.R
import com.nagy.movieapp.common.data.api.ApiConstants
import com.nagy.movieapp.common.utils.setImage
import com.nagy.movieapp.databinding.FragmentFindMovieBinding
import com.nagy.movieapp.databinding.FragmentMovieDetailsBinding
import com.nagy.movieapp.findmovie.presentation.FindMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.recycler_view_movie_item.view.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {


    private val binding get() = _binding!!
    private var _binding: FragmentMovieDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = MovieDetailsFragmentArgs.fromBundle(requireArguments()).movieDetails

        with(binding){
            movieOverView.text = movie.overview
            moviePoster.setImage(ApiConstants.LOAD_IMAGES_ENDPOINT + movie.posterPath)
            movieName.text = movie.originalTitle
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}