package com.nagy.movieapp.findmovie.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nagy.movieapp.R
import com.nagy.movieapp.common.domain.model.movie.MovieDetails
import com.nagy.movieapp.databinding.FragmentFindMovieBinding
import com.nagy.movieapp.findmovie.presentation.adapters.MovieClickListener
import com.nagy.movieapp.findmovie.presentation.adapters.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindMovieFragment : Fragment() {


    private val binding get() = _binding!!
    private var _binding: FragmentFindMovieBinding? = null
    private lateinit var alertDialog: AlertDialog.Builder
    private val viewModel: FindMovieViewModel  by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFindMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        requestInitialMoviesList()
    }

    private fun requestInitialMoviesList() {
        viewModel.onEvent(FindMoviesEvent.RequestNextMovie)
    }


    private fun requestMoreMovies() {
        viewModel.onEvent(FindMoviesEvent.RequestNextMovie)
    }


    private fun setupUI() {
        viewModel.init()
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewStateUpdates(adapter)
        observeViewEffects()
        alertDialog = AlertDialog.Builder(requireContext())
    }

    private fun observeViewEffects() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.viewEffects.collect { reactTo(it) }
        }
    }

    private fun reactTo(effect: FindMoviesViewEffect) {
        when (effect) {
            is FindMoviesViewEffect.ShowError -> handleViewError(effect.errorMessage)
            is FindMoviesViewEffect.ShowNetworkConnectionError -> snackBarErrorHandler(effect.errorMessage)
        }
    }


    private fun observeViewStateUpdates(adapter: MoviesAdapter) {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.viewState.collect { render(it, adapter) }
            }
    }

    private fun render(state: FindMovieViewState, adapter: MoviesAdapter) {
        binding.progressBar.isVisible = state.loading
        adapter.submitList(state.movies)
    }

    private fun createAdapter(): MoviesAdapter {
        return MoviesAdapter().apply {
            setMovieClickListener(object : MovieClickListener {
                override fun onClick(movieDetails: MovieDetails) {
                   val action = FindMovieFragmentDirections
                        .actionFindMovieFragmentToMovieDetailsFragment(movieDetails)
                    findNavController().navigate(action)
                }
            })
        }
    }

    private fun setupRecyclerView(moviesAdapter : MoviesAdapter) {
        binding.animalsRecyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false)
            setHasFixedSize(true)
            addOnScrollListener(createInfiniteScrollListener(layoutManager as LinearLayoutManager))
        }
    }

    private fun createInfiniteScrollListener(
        layoutManager: LinearLayoutManager
    ): RecyclerView.OnScrollListener {
        return object : InfiniteScrollListener(
            layoutManager,
            FindMovieViewModel.UI_PAGE_SIZE
        ) {
            override fun loadMoreItems() { requestMoreMovies() }
            override fun isLoading(): Boolean = viewModel.isLoadingMoreAnimals
            override fun isLastPage(): Boolean = false
        }
    }


    private fun snackBarErrorHandler(message: String) {

        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun handleViewError(errorMessage: String) {
        alertDialog.apply {
            setTitle(getString(R.string.error))
            setMessage(errorMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.dismiss)) {

                    dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        }.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
