package com.example.myapplication.ui.auth.presentaiton.authentication.user_detail



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.auth.domain.entities.Response
import com.example.myapplication.ui.auth.presentaiton.authentication.user_detail.component.TopBar
import com.example.myapplication.ui.auth.presentaiton.authentication.user_detail.component.UserDetailContent
import com.example.myapplication.utils.AlertDialogSample
import com.google.firebase.auth.FirebaseAuth

//This Active :)
@Composable
fun UserDetailScreen(
    viewModel: UserDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,

    navToUpdate:()-> Unit,
    onMenuClick:()-> Unit ,
    navigateToSignIn :()-> Unit ,
    navigateToHome:()-> Unit


) {


    if (FirebaseAuth.getInstance().uid == null){
        AlertDialogSample(
            title = "SignIn",
            text = "Please SignIn To See Your User Profile ",
            onConfirmClick = navigateToSignIn ,
            onDismissClick = navigateToHome
        )
    }


    val user by viewModel.userState
    val isLoading by viewModel.loadingState
    val error by viewModel.errorState
    val logOutState by viewModel.logOutState.collectAsState()

    LaunchedEffect(logOutState){
        when(logOutState){
            is Response.Success->{
               navigateToSignIn
                viewModel.resetLogoutState()
            }
            is Response.Error -> {
                viewModel.resetLogoutState()
            }
            else -> {

            }
        }
    }


    Scaffold(
        topBar = {
            TopBar(
                onLogOutClick = {viewModel.logout()} ,
                onMenuClick = onMenuClick

            )
        }
    ) { innerPadding ->
        UserDetailContent(
            user = user,
            isLoading = isLoading,
            error = error,
            onBackClick = onBackClick,
            onRetry = {},
            enableTextField = true,
            buttonString = "Update User Information",
            modifier = Modifier.padding(innerPadding),
            onLogOutClick = {
                viewModel.logout()
            },
            onUpdateUser = { updatedUser ->
                viewModel.updateUserDetail(updatedUser)
            },
        )
        if (logOutState is Response.Loading) {
            CircularProgressIndicator()
        }

    }
}

