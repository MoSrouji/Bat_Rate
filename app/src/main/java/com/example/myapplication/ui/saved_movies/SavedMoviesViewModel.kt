package com.example.myapplication.ui.saved_movies

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.movie_detail.domain.models.MovieDetail
import com.example.myapplication.movie_detail.domain.repository.MovieDetailRepository
import com.example.myapplication.save_list.domain.repository.SaveListRepo
import com.example.myapplication.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.myapplication.auth.domain.repositories.AuthRepository
import com.example.myapplication.auth.user_detail.domain.repository.UserDetailRepo
import com.example.myapplication.ui.detail.WatchLaterUiState

@HiltViewModel
class SavedMoviesViewModel @Inject constructor(
    private val savedRepository: SaveListRepo ,
    private val repository : MovieDetailRepository,
) : ViewModel() {



    private val _watchLaterState = MutableStateFlow(SavedMoviesState())
    val watchLaterState: StateFlow<SavedMoviesState> = _watchLaterState.asStateFlow()
    private val _watchedState = MutableStateFlow(SavedMoviesState())
    val watchedState: StateFlow<SavedMoviesState> = _watchedState.asStateFlow()

    init {
        loadSavedMovies("saveToWatchLater",_watchLaterState)
        loadSavedMovies("saveToWatched",_watchedState)
    }

   internal fun loadSavedMovies(label: String , state: MutableStateFlow<SavedMoviesState> ) {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true, error = null) }

            try {
                val savedMovies = savedRepository.getSavedMovies(label)
                Log.d("SavedMovies", "Loaded saved movies: ${savedMovies.size}")

                state.update {
                    it.copy(
                        isLoading = false,
                        movieIds = savedMovies,
                        isEmpty = savedMovies.isEmpty(),
                        error = null
                    )

                }
                if (savedMovies.isNotEmpty()){
                    fetchMovieDetails(savedMovies , state)
                }
            } catch (e: Exception) {
                Log.e("SavedMovies", "Error loading saved movies: ${e.message}", e)
                _watchLaterState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load saved movies",
                        isEmpty = true
                    )
                }
            }
        }
    }





//    fun refresh() {
//        loadSavedMovies(label = "saveToWatched")
//    }
//
//    fun clearError() {
//        _watchLaterState.update { it.copy(error = null) }
//    }


    private fun fetchMovieDetails(movieIds: List<Int> , state: MutableStateFlow<SavedMoviesState>) {
        viewModelScope.launch {
            state.update { it.copy(isMovieLoading = true, error = null) }

            val movieDetails = mutableListOf<MovieDetail>()
            val errors = mutableListOf<String>()

            movieIds.forEach { movieId ->
                repository.fetchMovieDetail(movieId).collectAndHandle(
                    onError = { error ->
                        val errorMsg = "Failed to load movie $movieId: ${error?.message}"
                        errors.add(errorMsg)
                        Log.e("SavedMovies", errorMsg)
                    },
                    onLoading = {
                        // Individual movie loading state if needed
                    },
                     { movieDetail ->
                        movieDetails.add(movieDetail)
                       state.update { currentState ->
                            currentState.copy(
                                movieDetailList = currentState.movieDetailList + movieDetail,
                                isMovieLoading = movieDetails.size < movieIds.size
                            )
                        }
                    }
                )
            }

            // Final state update
          state.update {
                it.copy(
                    isMovieLoading = false,
                    error = if (errors.isNotEmpty()) errors.joinToString("\n") else null,
                    success = errors.isEmpty() && movieDetails.isNotEmpty()
                )
            }
        }
    }







}

data class SavedMoviesState(
    val isLoading: Boolean = false,
    val movieIds: List<Int> = emptyList(),
    val movieDetail: MovieDetail? = null,
    val movieDetailList:List<MovieDetail> =emptyList(),
    val isEmpty: Boolean = false,
    val error: String? = null ,
    val isMovieLoading: Boolean =false,
    val success: Boolean = false
)



