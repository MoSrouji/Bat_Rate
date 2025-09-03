 package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.AppScreen
import com.example.myapplication.navigation.LunchMovieApp
import com.example.myapplication.ui.saved_movies.SavedMoviesViewModel
import com.example.myapplication.ui.splash.SplashViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


 @AndroidEntryPoint
class MainActivity : ComponentActivity() {

     private val splashViewModel : SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)

        }

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashViewModel.isLoading.value
            }
        }


        setContent {
                        MovieApp()

       }
    }



}

@Composable
fun MovieApp() {
    MyApplicationTheme {
        // Use Surface as the root container for proper theming
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            val navController = rememberNavController()
//            MainScreen(navController = navController ,
//                onSearchClick = {navController.navigate(AppScreen.SearchScreen().route)} ,
//                onUserButtonClick = {navController.navigate(AppScreen.UserDetailScreen().route)})
            LunchMovieApp(navController)
        }
    }
}