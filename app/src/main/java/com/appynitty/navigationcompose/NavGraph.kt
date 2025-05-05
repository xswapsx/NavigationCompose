package com.appynitty.navigationcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appynitty.navigationcompose.ui.first_screen.FirstScreen
import com.appynitty.navigationcompose.ui.second_screen.SecondScreen

@Composable
fun MyNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = Destinations.FIRST_SCREEN_ROUTE) {
        composable(Destinations.FIRST_SCREEN_ROUTE) {
            FirstScreen(navController = navController)
        }
        composable(Destinations.SECOND_SCREEN_ROUTE) {
            SecondScreen(navController = navController)
        }
    }
}