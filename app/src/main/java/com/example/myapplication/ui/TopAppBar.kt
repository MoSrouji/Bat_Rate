package com.example.myapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MovieCreation
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.home.itemSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TopAppBar(
    onSearchClick: () -> Unit,
    onUserButtonClick: () -> Unit,
    onMenuClick: () -> Unit,
) {
    TopAppBar(
        title = { Text("Bat-Rate", maxLines = 1, overflow = TextOverflow.Ellipsis) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                )
            }
        },
        actions = {
            IconButton(onClick =  onSearchClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                )
            }
            IconButton(onClick = onUserButtonClick) {
                Icon(
                    imageVector = Icons.Filled.PersonPin,
                    contentDescription = "User Profile",
                )
            }
        },
    )


}


@Composable
fun DrawerContent(
    onClose: () -> Unit,
    onBatRateClick: () -> Unit,
    onSearchClick: () -> Unit,
    onDiscoverMovieClick: () -> Unit,
    onTrendingMovieClick: () -> Unit,
    onWatchLaterMovieClick: () -> Unit,
    onWatchedMovieClick: () -> Unit,
    onAboutUsClick: () -> Unit,
    onNavigateBack:()-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DrawerCard(
            onClick = onBatRateClick,
            imageVector = Icons.Default.Home,
            contentDescriptor = "Bat-Rate",
            text = "Bat-Rate ",

            )
        Spacer(modifier = Modifier.height(16.dp))
        // Add your drawer items here
        DrawerCard(
            onClick = onSearchClick,
            imageVector = Icons.Default.Search,
            contentDescriptor = "Search",
            text = "Search ",
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "More Movies ",
            style = MaterialTheme.typography.headlineSmall
        )

        DrawerDoubleCard(
            onClickOne = onDiscoverMovieClick,
            onClickTwo = onTrendingMovieClick,
            imageVectorOne = Icons.Default.MovieCreation,
            contentDescriptorOne = "Discover Movies",
            textOne = "Discover Movies ",
            imageVectorTwo = Icons.AutoMirrored.Filled.TrendingUp,
            contentDescriptorTwo = "Trending Movies",
            textTwo = "Trending Movies  ",
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Saved Items ",
            style = MaterialTheme.typography.headlineSmall
        )

        DrawerDoubleCard(
            onClickOne = onWatchLaterMovieClick,
            onClickTwo = onWatchedMovieClick,
            imageVectorOne = Icons.Default.Bookmarks,
            contentDescriptorOne = "Watch Later Movies",
            textOne = "Watch Later Movies  ",
            imageVectorTwo = Icons.Default.RemoveRedEye,
            contentDescriptorTwo = "Watched Movies",
            textTwo = "Watched Movies  ",
        )

        Spacer(modifier = Modifier.height(16.dp))

        DrawerCard(
            onClick = onAboutUsClick,
            imageVector = Icons.Default.AccountBox,
            contentDescriptor = "About Us",
            text = "About Us  ",
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Button(onClick = onClose) {
            Text("Close Drawer")
        }
    }
}


@Composable
fun DrawerCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescriptor: String,
    text: String
) {
    Card(
        modifier = Modifier
            .padding(itemSpacing)
            .height(70.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {


            Row(
                modifier = Modifier
                    .clickable(
                        enabled = true,
                        onClick = onClick
                    )
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {

                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescriptor

                )
                Spacer(modifier = Modifier.padding(2.dp))

                Text(
                    text = text,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                )


            }
        }
    }

}

@Composable
fun DrawerDoubleCard(
    onClickOne: () -> Unit,
    onClickTwo: () -> Unit,
    modifier: Modifier = Modifier,
    imageVectorOne: ImageVector,
    contentDescriptorOne: String,
    textOne: String,
    imageVectorTwo: ImageVector,
    contentDescriptorTwo: String,
    textTwo: String
) {

    Card(
        modifier = Modifier
            .padding(itemSpacing)
            .height(120.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .clickable(
                        enabled = true,
                        onClick = onClickOne
                    )
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Icon(
                    imageVector = imageVectorOne,
                    contentDescription = contentDescriptorOne

                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = textOne,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                )


            }
            HorizontalDivider(thickness = 2.dp, modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier
                    .clickable(
                        enabled = true,
                        onClick = onClickTwo
                    )
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Icon(
                    imageVector = imageVectorTwo,
                    contentDescription = contentDescriptorTwo

                )
                Spacer(modifier = Modifier.padding(2.dp))

                Text(
                    text = textTwo,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                )


            }
        }
    }
}


@Composable
@Preview
fun DrawerCardPreview() {
    DrawerContent(
        onClose = {},
        onBatRateClick = {},
        onSearchClick = {},
        onDiscoverMovieClick = {},
        onTrendingMovieClick = {},
        onWatchLaterMovieClick = {},
        onWatchedMovieClick = {},
        onAboutUsClick = {} ,
        onNavigateBack = {}
    )
}

