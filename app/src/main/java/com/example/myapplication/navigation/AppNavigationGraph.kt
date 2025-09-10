package com.example.myapplication.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing

import androidx.compose.animation.core.tween

import androidx.compose.animation.slideInHorizontally

import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.DrawerContent
import com.example.myapplication.ui.TopAppBar
import com.example.myapplication.ui.aboutUs.AboutUs
import com.example.myapplication.ui.auth.presentaiton.authentication.signin.SignInScreen
import com.example.myapplication.ui.auth.presentaiton.authentication.signup.SignUpScreen
import com.example.myapplication.ui.auth.presentaiton.authentication.user_detail.UpdateUserDetailScreen
import com.example.myapplication.ui.detail.MovieDetailScreen
import com.example.myapplication.ui.home.HomeScreen
import com.example.myapplication.ui.moviesScreenDiscoverAndTrending.DiscoverMoviesScreen
import com.example.myapplication.ui.moviesScreenDiscoverAndTrending.TrendingMoviesScreen
import com.example.myapplication.ui.search.SearchScreen
import com.example.myapplication.ui.auth.presentaiton.authentication.user_detail.UserDetailScreen
import com.example.myapplication.ui.saved_movies.WatchLaterMoviesScreen
import com.example.myapplication.ui.saved_movies.WatchedMoviesScreen
import com.example.myapplication.utils.K
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlin.math.log

object NavAnimations {
    // Default animation specs
    private const val DEFAULT_DURATION = 300
    private val DEFAULT_EASING = FastOutSlowInEasing

    // Slide animations
    fun slideInFromRight() = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(DEFAULT_DURATION, easing = DEFAULT_EASING)
    )

    fun slideOutToLeft() = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(DEFAULT_DURATION, easing = DEFAULT_EASING)
    )

}

fun navigateToHomeScreen(navController: NavHostController) {
    navController.navigate(AppScreen.HomeScreen().route) {
        launchSingleTop = true

    }
}
fun navigateToSignInScreen(navController: NavHostController) {
    navController.navigate(AppScreen.SignInScreen().route) {
        launchSingleTop = true

    }
}
fun navigateToSignUpScreen(navController: NavHostController) {
    navController.navigate(AppScreen.SignUpScreen().route) {
        launchSingleTop = true

    }
}

fun navigateToUserScreen(navController: NavHostController) {
    navController.navigate(AppScreen.UserDetailScreen().route) {
        launchSingleTop = true

    }
}

fun navigateToDiscoverMoviesScreen(navController: NavHostController) {
    navController.navigate(AppScreen.DiscoverMovieScreen().route) {
        launchSingleTop = true

    }
}

fun navigateToTrendingMoviesScreen(navController: NavHostController) {
    navController.navigate(AppScreen.TrendingMovieScreen().route) {
        launchSingleTop = true

    }
}

fun navigateToSearchScreen(navController: NavHostController) {
    navController.navigate(AppScreen.SearchScreen().route) {
        launchSingleTop = true

    }
}

fun navigateToWatchLaterMoviesScreen(navController: NavHostController) {
    navController.navigate(AppScreen.WatchLaterMoviesScreen().route) {
        launchSingleTop = true

    }
}

fun navigateToWatchedMoviesScreen(navController: NavHostController) {
    navController.navigate(AppScreen.WatchedMoviesScreen().route) {
        launchSingleTop = true

    }
}

fun navigateToMovieDetailScreen(navController: NavHostController, id: Int) {
    navController.navigate(AppScreen.MovieDetailScreen().getRouteWithArgs(id)) {
        launchSingleTop = true

    }
}


// Usage in your NavHost:
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchMovieApp(
    navController: NavHostController,

    ) {


    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(
                onClose = {
                    scope.launch {
                        drawerState.close()
                    }
                },
                onBatRateClick = {

                    scope.launch {
                        drawerState.close()
                    }
                    navigateToHomeScreen(navController)

                },
                onSearchClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navigateToSearchScreen(navController)

                },
                onDiscoverMovieClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navigateToDiscoverMoviesScreen(navController)
                },
                onTrendingMovieClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navigateToTrendingMoviesScreen(navController)
                },
                onWatchLaterMovieClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navigateToWatchLaterMoviesScreen(navController)
                },
                onWatchedMovieClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navigateToWatchedMoviesScreen(navController)
                },
                onAboutUsClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(
                        AppScreen.AboutUsScreen().route
                    )
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        },
        modifier = Modifier.width(150.dp),
        drawerState = drawerState,
        gesturesEnabled = false,
        scrimColor = MaterialTheme.colorScheme.background,
    ) {


        NavHost(
            navController = navController,
            startDestination = if (FirebaseAuth.getInstance().uid != null) {
                AppScreen.HomeScreen().route


            } else {
                AppScreen.SignInScreen().route

            },
            enterTransition = { NavAnimations.slideInFromRight() },
            exitTransition = { NavAnimations.slideOutToLeft() },
            popEnterTransition = { NavAnimations.slideInFromRight() },
            popExitTransition = { NavAnimations.slideOutToLeft() }
        ) {


            composable(
                AppScreen.SignInScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }
            ) {


                SignInScreen(
                    navController = navController,
                    navToHomeScreen = { navigateToHomeScreen(navController) }
                )

            }


            composable(
                AppScreen.SignUpScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }

            ) {

                SignUpScreen(
                    navController = navController,
                    navToHomeScreen = {
                        navigateToHomeScreen(navController)

                    }

                )
            }


            // Home Screen Nav
            composable(
                AppScreen.HomeScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }

            ) {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            onSearchClick = {
                                navigateToSearchScreen(navController)

                            },
                            onUserButtonClick = {
                                navigateToUserScreen(navController)

                            },
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }) {

                    HomeScreen(
                        modifier = Modifier.padding(top = 25.dp),
                        onMovieClick = {
                            navigateToMovieDetailScreen(navController, it)
                        },
                        onDiscoverArrowClick = {
                            navigateToDiscoverMoviesScreen(navController)
                        },
                        onTradingArrowClick = {
                            navigateToTrendingMoviesScreen(navController)
                        }


                    )

                }
            }


            // Discover Movies Screen Nav
            composable(
                AppScreen.DiscoverMovieScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            onSearchClick = {
                                navigateToSearchScreen(navController)

                            },
                            onUserButtonClick = {
                                navigateToUserScreen(navController)
                            },
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }) {
                    DiscoverMoviesScreen(
                        onMovieClick = {
                            navigateToMovieDetailScreen(navController, it)
                        },
                        onNavigateUP = { navController.navigateUp() }
                    )
                }
            }


            // Trending Movies Screen Nav
            composable(
                AppScreen.TrendingMovieScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() })
            {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            onSearchClick = {
                                navigateToSearchScreen(navController)

                            },
                            onUserButtonClick = {
                                navigateToUserScreen(navController)

                            },
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }) {
                    TrendingMoviesScreen(

                        onMovieClick = {
                            navigateToMovieDetailScreen(navController, it)
                        },
                        onNavigateUP = { navController.navigateUp() }
                    )
                }
            }

            // Film Screen Nav
            composable(
                AppScreen.MovieDetailScreen().routeWithArgs,
                arguments = listOf(navArgument(name = K.MOVIE_ID) {
                    type = NavType.IntType
                }),
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }
            ) {movie->
                Scaffold(
                    topBar = {
                        TopAppBar(
                            onSearchClick = {
                                navigateToSearchScreen(navController)

                            },
                            onUserButtonClick = {
                              navigateToUserScreen(navController)
                            },
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }) {
                    MovieDetailScreen(
                        onNavigateUP = {
                            navController.navigateUp()
                        },
                        onMovieClick = {
                            navController.navigate(
                                AppScreen.MovieDetailScreen().getRouteWithArgs(it)
                            ) {
                                launchSingleTop = true
                                navController.navigate(AppScreen.HomeScreen().route)

                            }

                        },
                        onActorClick = {

                        },

                        navToSignIn = {navigateToSignInScreen(navController)}
                    )
                }
            }



            composable(
                AppScreen.UserDetailScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }) {

                UserDetailScreen(
                    onBackClick = { navController.navigateUp() },

                    navToUpdate = {
                        navController.navigate(AppScreen.UpdateUserDetailScreen().route) {
                            launchSingleTop = true
                        }
                    },
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    navigateToSignIn = {
                     navigateToSignInScreen(navController)
                    },
                    navigateToHome = {
                        navigateToHomeScreen(navController)
                    }
                )
            }


            composable(
                AppScreen.UpdateUserDetailScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }
            ) {
                UpdateUserDetailScreen(
                    onBackClick = { navController.navigateUp() },
                    onLogoutSuccess = {navigateToSignInScreen(navController)
                    },
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                )
            }

            composable(
                AppScreen.SearchScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }
            ) {


                SearchScreen(
                    onMovieClick = {navigateToMovieDetailScreen(navController,it)
                    },
                )
            }

            composable(
                AppScreen.WatchLaterMoviesScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            onSearchClick = {
                                navigateToSearchScreen(navController)

                            },
                            onUserButtonClick = {navigateToUserScreen(navController)

                            },
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }) {
                    WatchLaterMoviesScreen(
                        onMovieClick = {
                           navigateToMovieDetailScreen(navController,it)
                        },
                        navToSignIn = {
                            navController.navigate(
                                AppScreen.SignInScreen().route
                            ) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }

                            }
                        },
                        navToHome = {
                            navController.navigate(
                                AppScreen.HomeScreen().route
                            ) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }
                    )


                }
            }



            composable(
                AppScreen.WatchedMoviesScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            onSearchClick = {
                                navigateToSearchScreen(navController)

                            },
                            onUserButtonClick = {
                               navigateToUserScreen(navController)
                            },
                            onMenuClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }) {
                    WatchedMoviesScreen(
                        onMovieClick = {
                           navigateToMovieDetailScreen(navController,it)
                        },
                        navToSignIn = {
                            navController.navigate(
                                AppScreen.SignInScreen().route
                            ) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        },
                        navToHome = {
                            navController.navigate(
                                AppScreen.HomeScreen().route

                            ) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                            }
                        }
                    )


                }
            }
            composable(  AppScreen.AboutUsScreen().route,
                enterTransition = { NavAnimations.slideInFromRight() },
                exitTransition = { NavAnimations.slideOutToLeft() }
            ){
                AboutUs()

            }


        }

    }


}



