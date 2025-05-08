package com.appynitty.navigationcompose

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appynitty.navigationcompose.ui.first_screen.FirstScreen
import com.appynitty.navigationcompose.ui.second_screen.SecondScreen
import com.appynitty.navigationcompose.util.AppModalDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyNavGraph(
    navController: NavHostController = rememberNavController(),
    navActions: NavigationActions = NavigationActions(navController),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    startDestination: String = Destinations.FIRST_SCREEN_ROUTE
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Destinations.FIRST_SCREEN_ROUTE) {
            AppModalDrawer(drawerState, currentRoute, navActions) {
                FirstScreen(
                    navigationActions = navActions,
                    openDrawer = { coroutineScope.launch { drawerState.open() } })
            }
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
            AppModalDrawer(drawerState, currentRoute, navActions) {
                SecondScreen(
                    navActions = navActions,
                    userName = userName,
                    openDrawer = { coroutineScope.launch { drawerState.open() } })
            }
        }
    }
}