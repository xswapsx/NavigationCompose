package com.appynitty.navigationcompose

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.appynitty.navigationcompose.DestinationsArgs.USER_NAME_ARG
import com.appynitty.navigationcompose.Screens.FIRST_SCREEN
import com.appynitty.navigationcompose.Screens.SECOND_SCREEN

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
    const val FIRST_SCREEN_ROUTE = FIRST_SCREEN
    const val SECOND_SCREEN_ROUTE = "${SECOND_SCREEN}/{${USER_NAME_ARG}}"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigateToFirstScreen() {
        if (navController.currentDestination?.route != Destinations.FIRST_SCREEN_ROUTE) {
            navController.navigate(Destinations.FIRST_SCREEN_ROUTE) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    fun navigateToSecondScreen(userName: String) {
        val route = "${SECOND_SCREEN}/$userName"
        if (navController.currentDestination?.route != Destinations.SECOND_SCREEN_ROUTE) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
            }
        }
    }
}