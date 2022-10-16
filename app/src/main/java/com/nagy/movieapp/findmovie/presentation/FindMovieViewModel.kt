package com.nagy.movieapp.findmovie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nagy.movieapp.common.domain.model.NetworkException
import com.nagy.movieapp.common.domain.model.NetworkUnavailableException
import com.nagy.movieapp.common.domain.model.NoMoreMovieException
import com.nagy.movieapp.common.utils.DispatchersProvider
import com.nagy.movieapp.common.utils.createExceptionHandler
import com.nagy.movieapp.findmovie.domain.usecases.GetMovies
import com.nagy.movieapp.findmovie.domain.usecases.RequestNextMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FindMovieViewModel @Inject constructor(
    private val getMovies: GetMovies,
    private val requestNextMovie: RequestNextMovie,
    private val dispatchersProvider: DispatchersProvider
) :ViewModel()  {
    companion object {
        const val INIT_MOVIE_ID = 550L
        const val UI_PAGE_SIZE = 1
    }
    private var currentMovie =   550L
    var isLoadingMoreAnimals: Boolean = false

    fun init() {
        subscribeToMoviesUpdates()

        if (viewState.value.movies.isNotEmpty()){
            currentMovie = viewState.value.movies.last().id
        }
    }
    val viewState: StateFlow<FindMovieViewState> get() = _viewState
    val viewEffects: SharedFlow<FindMoviesViewEffect> get() = _viewEffects

    private val _viewState = MutableStateFlow(FindMovieViewState())
    private val _viewEffects = MutableSharedFlow<FindMoviesViewEffect>()

    fun onEvent(event: FindMoviesEvent) {
        when (event) {
            is FindMoviesEvent.RequestNextMovie -> loadMoreMovies()
        }
    }

    private fun subscribeToMoviesUpdates() {

        val errorMessage = "Failed to fetch data from Cache"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        viewModelScope.launch(exceptionHandler) {
            getMovies().collect {
                _viewState.value = viewState.value.copy(loading = false, movies = it)
            }
        }
    }

    private fun loadMoreMovies() {
        isLoadingMoreAnimals = true
        _viewState.value = viewState.value.copy(loading = true)
        val errorMessage = "Failed to fetch Jobs"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }
        viewModelScope.launch(exceptionHandler) {
            withContext(dispatchersProvider.io()) {
                requestNextMovie(++currentMovie)
            }
        }
        isLoadingMoreAnimals = false
    }

    private fun onFailure(failure: Throwable) {

        viewModelScope.launch {
            when (failure) {
                is NetworkException, is NetworkUnavailableException -> {
                    _viewEffects.emit(FindMoviesViewEffect.ShowError("No internet connection"))
                }
                is NoMoreMovieException -> {
                    _viewEffects.emit(
                            FindMoviesViewEffect.ShowNetworkConnectionError("Failed to fetch Movie information")
                        )
                }
            }
        }

    }
}






