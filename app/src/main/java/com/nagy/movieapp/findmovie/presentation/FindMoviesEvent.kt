package com.nagy.movieapp.findmovie.presentation

sealed class FindMoviesEvent {
    object RequestNextMovie : FindMoviesEvent()
}