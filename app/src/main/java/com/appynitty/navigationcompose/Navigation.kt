package com.appynitty.navigationcompose

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Screens used in [Destinations]
 */
private object Screens {
    const val FIRST_SCREEN = "firstScreen"
    const val SECOND_SCREEN = "secondScreen"
}

/**
 * Arguments used in [Destinations] routes
 */
object DestinationsArgs {
    const val USER_NAME_ARG = "userName"
    const val USER_ID_ARG = "userId"
}

/**
 * Destinations used in the app
 */
object Destinations {
    const val FIRST_SCREEN_ROUTE = Screens.FIRST_SCREEN
    const val SECOND_SCREEN_ROUTE = Screens.SECOND_SCREEN
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigateToFirstScreen() {
        navController.navigate(Destinations.FIRST_SCREEN_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToSecondScreen() {
        navController.navigate(Destinations.SECOND_SCREEN_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}