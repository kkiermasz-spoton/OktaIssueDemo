package com.kkiermasz.oktaissuedemo

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.kkiermasz.oktaissuedemo.login.LoginRoute
import com.kkiermasz.oktaissuedemo.login.loginGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NavigationComponent(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = LoginRoute.id,
        ) {
            loginGraph(navController)
        }
    }
}

interface NavigationRoute {
    val id: String
    val title: String
}

inline fun NavGraphBuilder.navigation(
    startRoute: NavigationRoute,
    route: NavigationRoute,
    noinline builder: NavGraphBuilder.() -> Unit
): Unit = navigation(startDestination = startRoute.id, route = route.id, builder = builder)
