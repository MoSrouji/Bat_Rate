package com.example.myapplication.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.movie.domain.models.Movie
import com.example.myapplication.ui.home.itemSpacing
import com.example.myapplication.ui.saved_movies.SavedMoviesViewModel
import com.example.myapplication.utils.K

@Composable
fun MovieCoverImage(
    modifier: Modifier = Modifier,
    savedMoviesViewModel: SavedMoviesViewModel = hiltViewModel(),
    movie: Movie,
    onMovieClick: (Int) -> Unit,
    onSaveButtonClick: (Int) -> Unit,
    iconButton: (Int) -> ImageVector
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data("${K.BASE_IMAGE_URL}${movie.posterPath}")
        .crossfade(true)
        .build()


    val uiState by savedMoviesViewModel.watchLaterState.collectAsState()


    Box(
        modifier = modifier
            .size(width = 150.dp, height = 250.dp)
            .padding(itemSpacing)
            .clickable { onMovieClick(movie.id) }) {
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.shapes.medium)
                .shadow(elevation = 4.dp),
            contentScale = ContentScale.Crop
        )
        MovieCard(
            shapes = CircleShape,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        ) {

            IconButton(
                onClick = { onSaveButtonClick(movie.id) }
            ) {
                Icon(
                    imageVector = iconButton(movie.id),
                    contentDescription = "Bookmark",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
        Surface(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = .8f),
            contentColor = Color.White,
            shape = RoundedCornerShape(
                bottomEnd = 30.dp,
                bottomStart = 30.dp
            )
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1

                )
            }
        }


    }
}



