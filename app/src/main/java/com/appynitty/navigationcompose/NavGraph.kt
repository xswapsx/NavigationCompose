package com.appynitty.navigationcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appynitty.navigationcompose.ui.first_screen.FirstScreen
import com.appynitty.navigationcompose.ui.second_screen.SecondScreen

@Composable
fun MyNavGraph(
    navController: NavHostController = rememberNavController(),
    navigationActions: NavigationActions = NavigationActions(navController)
) {

    NavHost(navController = navController, startDestination = Destinations.FIRST_SCREEN_ROUTE) {
        composable(Destinations.FIRST_SCREEN_ROUTE) {
            FirstScreen(navigationActions = navigationActions)
        }
        composable(
            route = Destinations.SECOND_SCREEN_ROUTE,
            arguments = listOf(navArgument(DestinationsArgs.USER_NAME_ARG) {
                type = NavType.StringType
                defaultValue = "Guest"
            })
        ) { backStackEntry ->
            val userName =
                backStackEntry.arguments?.getString(DestinationsArgs.USER_NAME_ARG) ?: "Guest"
            SecondScreen(navigationActions = navigationActions, userName = userName)
        }
    }
}