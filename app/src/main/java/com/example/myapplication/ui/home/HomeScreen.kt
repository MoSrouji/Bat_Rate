package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.HiltViewModelFactory
import com.example.myapplication.movie.domain.models.Movie
import com.example.myapplication.movie_detail.domain.models.MovieDetail
import com.example.myapplication.navigation.NavAnimations
import com.example.myapplication.ui.components.LoadingView
import com.example.myapplication.ui.detail.DetailViewModel
import com.example.myapplication.ui.home.components.BodyContent
import com.example.myapplication.ui.home.components.TopContent
import com.example.myapplication.ui.saved_movies.SavedMoviesViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    savedViewModel: DetailViewModel = hiltViewModel(),
    loadSavedMoviesViewModel: SavedMoviesViewModel = hiltViewModel(),
    onMovieClick: (id: Int) -> Unit,
    onDiscoverArrowClick: () -> Unit,
    onTradingArrowClick: () -> Unit,

    ) {


    var isAutoScrolling by remember {
        mutableStateOf(true)
    }

    val state by homeViewModel.homeState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { state.discoverMovies.size }
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    val saveToWatchLaterLabel: String = "saveToWatchLater"

    val savedMoviesState by loadSavedMoviesViewModel.watchLaterState.collectAsStateWithLifecycle()
    val listIDs = savedMoviesState.movieIds
    val context = LocalContext.current




    LaunchedEffect(key1 = pagerState.currentPage) {
        if (isDragged) {
            isAutoScrolling = false
        } else {
            isAutoScrolling = true
            delay(5000)
            with(pagerState) {
                val target = if (currentPage < state.discoverMovies.size - 1) currentPage + 1 else 0
                scrollToPage(target)
            }


        }
    }

    Box(modifier = modifier) {


        AnimatedVisibility(
            visible = state.error != null,
            enter = NavAnimations.slideInFromRight(),
            exit = NavAnimations.slideOutToLeft()
        ) {
            Text(
                text = state.error ?: "Unknown error",
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )


        }
        AnimatedVisibility(
            visible = !state.isLoading && state.error == null,
            enter = NavAnimations.slideInFromRight(),
            exit = NavAnimations.slideOutToLeft()
        ) {

            BoxWithConstraints(modifier = modifier.fillMaxSize()) {
                val boxHeight = maxHeight
                val topItemHeight = boxHeight * .45f
                val bodyItemHeight = boxHeight * .55f
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(defaultPadding),
                    pageSpacing = itemSpacing
                ) { page ->
                    if (isAutoScrolling) {
                        AnimatedContent(targetState = page) { index ->
                            TopContent(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .heightIn(min = topItemHeight),
                                movie = state.discoverMovies[index],
                                onMovieClick = {
                                    onMovieClick(it)
                                }
                            )


                        }
                    } else {
                        TopContent(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .heightIn(min = topItemHeight),
                            movie = state.discoverMovies[page],
                            onMovieClick = {
                                onMovieClick(it)
                            }
                        )

                    }

                }


                BodyContent(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .heightIn(max = bodyItemHeight),
                    discoverMovies = state.discoverMovies,
                    trendingMovies = state.trendingMovies,
                    onMovieClick = onMovieClick,
                    onTradingArrowClick = onTradingArrowClick,
                    onDiscoverArrowClick = onDiscoverArrowClick,
                    onBookMarkClick = {
                        val userId = FirebaseAuth.getInstance().currentUser?.uid
                        if (userId != null) {
                            savedViewModel.addWatchLater(
                                labelName = saveToWatchLaterLabel,
                                it
                            )
                               Toast.makeText(context, "Movie saved", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(
                                context,
                                if (userId == null) "Please sign in" else "Movie data not available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    iconButton = { id ->

                        if (savedMoviesState.movieIds.contains(id)) {
                            Icons.Default.Bookmarks
                        } else {
                            Icons.Default.BookmarkAdd
                        }


                    }

                )


            }
        }


    }
    LoadingView(isLoading = state.isLoading)
}

