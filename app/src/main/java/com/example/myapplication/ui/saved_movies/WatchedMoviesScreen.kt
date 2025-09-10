package com.example.myapplication.ui.saved_movies


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.ui.home.itemSpacing
import com.example.myapplication.ui.saved_movies.component.SavedMoviesScreen

@Composable
fun WatchedMoviesScreen(
    viewModel: SavedMoviesViewModel = hiltViewModel(),
    navToSignIn: () -> Unit,
    navToHome: () -> Unit,
    onMovieClick: (Int) -> Unit
) {
    val uiState by viewModel.watchedState.collectAsStateWithLifecycle()



        SavedMoviesScreen(
            onMovieClick = onMovieClick,
            modifier = Modifier,
            state = uiState,
            navigateToSignIn = navToSignIn,
            navigateToHome = navToHome,
            pageName = "Watched Movies List ",
            viewModel = viewModel,
            label = "saveToWatched"

        )
    }
