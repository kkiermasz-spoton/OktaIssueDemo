package com.kkiermasz.oktaissuedemo.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.spoton.connectcompanion.android.features.account.ui.login.router.LoginScreenRouter

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    router: LoginScreenRouter
) {
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(router))
    val state by loginViewModel.uiState.collectAsState()
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Box(modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = true,
                    ambientColor = Color.White,
                    spotColor = Color.Black,
                )
                .background(
                    Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .height(200.dp)
                .padding(horizontal = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Content(loginViewModel)
            }
        }
    }
}

@Composable
private fun Content(
    viewModel: LoginViewModel
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            viewModel.oktaButtonTapped()
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = "Continue with OKTA")
        }
    }
}

private class LoginViewModelFactory(
    private val router: LoginScreenRouter
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T> ): T {
        if( modelClass.isAssignableFrom( LoginViewModel::class.java ) ) {
            @Suppress( "UNCHECKED_CAST" )
            return LoginViewModel(router) as T
        }
        throw IllegalArgumentException( "Unknown ViewModel Class" )
    }
}
