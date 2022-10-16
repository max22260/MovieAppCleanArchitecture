package com.nagy.movieapp.findmovie.presentation

sealed class FindMoviesViewEffect {
    data class ShowError(val errorMessage: String) : FindMoviesViewEffect()
    data class ShowNetworkConnectionError(val errorMessage: String) : FindMoviesViewEffect()
}
