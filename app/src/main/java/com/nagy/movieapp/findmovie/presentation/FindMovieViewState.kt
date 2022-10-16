package com.nagy.movieapp.findmovie.presentation

import com.nagy.movieapp.common.domain.model.movie.MovieDetails

data class FindMovieViewState(
    val loading : Boolean = false ,
    val movies : List<MovieDetails> = emptyList()
)
