package com.example.myapplication.ui.saved_movies.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.home.itemSpacing
import com.example.myapplication.movie_detail.domain.models.MovieDetail
import com.example.myapplication.ui.saved_movies.SavedMoviesState
import com.example.myapplication.ui.saved_movies.SavedMoviesViewModel
import com.example.myapplication.utils.AlertDialogSample
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SavedMoviesScreen(
    viewModel: SavedMoviesViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit,
    modifier: Modifier,
    state: SavedMoviesState,
    navigateToSignIn: () -> Unit,
    navigateToHome: () -> Unit,
    pageName: String,
    label: String

) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp),
        contentAlignment = Alignment.Center
    ) {
        if (FirebaseAuth.getInstance().uid == null) {
            AlertDialogSample(
                title = "SignIn",
                text = "Please SignIn To See Your Watch Later Movies ",
                onConfirmClick = navigateToSignIn,
                onDismissClick = navigateToHome
            )
        }


        when {
            state.isLoading -> {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.padding(itemSpacing))
                Text("Loading ")
            }

            state.isEmpty -> {
                Text("No saved movies found")

            }

            state.error != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Error: ${state.error}")
                    Button(onClick = { }) {
                        Text("Retry")
                    }
                }
            }

            else -> {
                // MovieIdsList(state.movieIds)
                Spacer(modifier = Modifier.padding(itemSpacing))


                when {
                    state.isMovieLoading -> {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.padding(itemSpacing))
                        Text("Loading")

                    }

                    state.error != null -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Error: ${state.error}")
                            Button(onClick = { }) {
                                Text("Retry")
                            }
                        }
                    }

                    else -> {
                        Spacer(modifier = Modifier.padding(itemSpacing))

                        ShowMovies(
                            state.movieDetailList,
                            onMovieClick = onMovieClick,
                            onNavigateUP = navigateToHome,
                            pageName = pageName,
                            onDeleteClick = {
                                viewModel.deleteMovie(it, label)
                                }
                        )
                    }
                }


            }
        }
    }
}


@Composable
fun ShowMovies(
    movies: List<MovieDetail>,
    onMovieClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onNavigateUP: () -> Unit,
    pageName: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(itemSpacing),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = onNavigateUP) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back To Home Screen"

                )
            }
            Text(
                text = pageName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(vertical = itemSpacing)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            items(movies) { movie ->
                MovieDetailCard(
                    movie = movie,
                    onMovieClick = onMovieClick,
                    onDeleteClick = onDeleteClick
                )
            }


        }
    }
}

