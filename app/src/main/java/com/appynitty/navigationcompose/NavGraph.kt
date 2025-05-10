package com.appynitty.navigationcompose

import androidx.activity.ComponentActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appynitty.navigationcompose.Destinations.ABOUT_US_SCREEN_ROUTE
import com.appynitty.navigationcompose.Destinations.SETTINGS_SCREEN_ROUTE
import com.appynitty.navigationcompose.ui.about_us_screen.AboutUsScreen
import com.appynitty.navigationcompose.ui.first_screen.FirstScreen
import com.appynitty.navigationcompose.ui.second_screen.SecondScreen
import com.appynitty.navigationcompose.ui.settings_screen.SettingsScreen
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
    var isDarkTheme by remember { mutableStateOf(false) }
    val context = LocalContext.current // Get context here

    // Callback to finish the app
    val finishApp: () -> Unit = {
        (context as? ComponentActivity)?.finish()
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = Destinations.FIRST_SCREEN_ROUTE,
            enterTransition = { fadeIn(animationSpec = tween(900)) },
            exitTransition = { fadeOut(animationSpec = tween(900)) },
            popEnterTransition = { fadeIn(animationSpec = tween(900)) },
            popExitTransition = { fadeOut(animationSpec = tween(900)) }) {
            AppModalDrawer(drawerState, currentRoute, navActions) {
                FirstScreen(
                    navigationActions = navActions,
                    openDrawer = { coroutineScope.launch { drawerState.open() } }, finishApp = finishApp)
            }
        }
        composable(
            route = Destinations.SECOND_SCREEN_ROUTE,
            enterTransition = { fadeIn(animationSpec = tween(900)) },
            exitTransition = { fadeOut(animationSpec = tween(900)) },
            popEnterTransition = { fadeIn(animationSpec = tween(900)) },
            popExitTransition = { fadeOut(animationSpec = tween(900)) },
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

        composable(
            route = SETTINGS_SCREEN_ROUTE,
            enterTransition = { fadeIn(animationSpec = tween(900)) },
            exitTransition = { fadeOut(animationSpec = tween(900)) },
            popEnterTransition = { fadeIn(animationSpec = tween(900)) },
            popExitTransition = { fadeOut(animationSpec = tween(900)) }) {
            AppModalDrawer(drawerState, currentRoute, navActions) {
                SettingsScreen(
                    navActions = navActions,
                    onThemeToggle = { newTheme ->
                        isDarkTheme = newTheme
                    },
                    onLanguageChange = {},
                    isDarkTheme = isDarkTheme
                )
            }
        }

        composable(
            route = ABOUT_US_SCREEN_ROUTE,
            enterTransition = { fadeIn(animationSpec = tween(900)) },
            exitTransition = { fadeOut(animationSpec = tween(900)) },
            popEnterTransition = { fadeIn(animationSpec = tween(900)) },
            popExitTransition = { fadeOut(animationSpec = tween(900)) }) {
            AppModalDrawer(drawerState, currentRoute, navActions) {
                AboutUsScreen(
                    navActions = navActions
                )
            }
        }
    }
}