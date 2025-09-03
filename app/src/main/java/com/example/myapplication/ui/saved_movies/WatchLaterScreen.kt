package com.example.myapplication.ui.saved_movies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.saved_movies.component.SavedMoviesScreen

@Composable
fun WatchLaterMoviesScreen(
    viewModel: SavedMoviesViewModel = hiltViewModel(),

    onMovieClick:(Int)-> Unit
){
    val uiState by viewModel.watchLaterState.collectAsState()
    SavedMoviesScreen(
        onMovieClick = onMovieClick,
        modifier = Modifier,
        state = uiState
    )
}