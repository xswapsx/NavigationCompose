package com.appynitty.navigationcompose

import androidx.navigation.NavHostController
import com.appynitty.navigationcompose.DestinationsArgs.USER_NAME_ARG
import com.appynitty.navigationcompose.Screens.ABOUT_US_SCREEN
import com.appynitty.navigationcompose.Screens.FIRST_SCREEN
import com.appynitty.navigationcompose.Screens.LOGIN_SCREEN
import com.appynitty.navigationcompose.Screens.SECOND_SCREEN
import com.appynitty.navigationcompose.Screens.SETTINGS_SCREEN
import com.appynitty.navigationcompose.Screens.SIGNUP_SCREEN
import com.appynitty.navigationcompose.Screens.WELCOME_SCREEN

/**
 * Screens used in [Destinations]
 */
private object Screens {
    const val WELCOME_SCREEN = "welcomeScreen"
    const val LOGIN_SCREEN = "loginScreen"
    const val SIGNUP_SCREEN = "signUpScreen"
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
    const val WELCOME_SCREEN_ROUTE = WELCOME_SCREEN
    const val LOGIN_SCREEN_ROUTE = LOGIN_SCREEN
    const val SIGNUP_SCREEN_ROUTE = SIGNUP_SCREEN
    const val FIRST_SCREEN_ROUTE = FIRST_SCREEN
    const val SETTINGS_SCREEN_ROUTE = SETTINGS_SCREEN
    const val ABOUT_US_SCREEN_ROUTE = ABOUT_US_SCREEN
    const val SECOND_SCREEN_ROUTE = "${SECOND_SCREEN}/{${USER_NAME_ARG}}"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(val navController: NavHostController) {
    fun navigateToWelcomeScreen() {
        navController.navigate(WELCOME_SCREEN) {
            navController.popBackStack()
            launchSingleTop = true
        }
    }

    fun navigateToLoginScreen() {
        navController.navigate(LOGIN_SCREEN) {
            launchSingleTop = true
        }
    }

    fun navigateToSignUpScreen() {
        navController.navigate(SIGNUP_SCREEN) {
            launchSingleTop = true
        }
    }

    fun navigateToFirstScreen() {
        navController.navigate(Destinations.FIRST_SCREEN_ROUTE) {

            popUpTo(Destinations.FIRST_SCREEN_ROUTE) {
                inclusive = false
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

    fun navigateToMainGraph() {
        navController.navigate("main") {
            popUpTo("onBoarding") { inclusive = true } // Clear the onboarding graph
            launchSingleTop = true
            restoreState = true
        }
    }

    fun goBack() {
        navController.popBackStack()
    }
}