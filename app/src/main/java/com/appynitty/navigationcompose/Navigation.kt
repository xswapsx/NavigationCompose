package com.appynitty.navigationcompose

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.appynitty.navigationcompose.DestinationsArgs.USER_NAME_ARG
import com.appynitty.navigationcompose.Screens.ABOUT_US_SCREEN
import com.appynitty.navigationcompose.Screens.FIRST_SCREEN
import com.appynitty.navigationcompose.Screens.SECOND_SCREEN
import com.appynitty.navigationcompose.Screens.SETTINGS_SCREEN

/**
 * Screens used in [Destinations]
 */
private object Screens {
    const val FIRST_SCREEN = "firstScreen"
    const val SECOND_SCREEN = "secondScreen"
    const val SETTINGS_SCREEN = "settingsScreen"
    const val ABOUT_US_SCREEN = "aboutUsScreen"
}

/**
 * Arguments used in [Destinations] routes
 */
object DestinationsArgs {
    const val USER_NAME_ARG = "userName"
//    const val USER_ID_ARG = "userId"
}

/**
 * Destinations used in the app
 */
object Destinations {
    const val FIRST_SCREEN_ROUTE = FIRST_SCREEN
    const val SETTINGS_SCREEN_ROUTE = SETTINGS_SCREEN
    const val ABOUT_US_SCREEN_ROUTE = ABOUT_US_SCREEN
    const val SECOND_SCREEN_ROUTE = "${SECOND_SCREEN}/{${USER_NAME_ARG}}"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(val navController: NavHostController) {

    fun navigateToFirstScreen() {
        navController.navigate(Destinations.FIRST_SCREEN_ROUTE) {
            // Clear the back stack up to FirstScreen
            popUpTo(Destinations.FIRST_SCREEN_ROUTE) {
                inclusive = false // Keep FirstScreen in the stack
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToSecondScreen(userName: String) {
        val route = "${SECOND_SCREEN}/$userName"
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToSettingsScreen() {
        navController.navigate(Destinations.SETTINGS_SCREEN_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToAboutUsScreen() {
        navController.navigate(Destinations.ABOUT_US_SCREEN_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun goBack() {
        navController.popBackStack()
    }
}