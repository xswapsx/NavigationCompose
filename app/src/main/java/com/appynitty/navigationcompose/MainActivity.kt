package com.appynitty.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appynitty.navigationcompose.ui.first_screen.FirstScreen
import com.appynitty.navigationcompose.ui.theme.NavigationComposeTheme
import com.appynitty.navigationcompose.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetSystemBarsAppearance(statusBarColor = Purple40)
            enableEdgeToEdge()
            MyNavGraph()
            /*NavigationComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    *//*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*//*
                    FirstScreen(modifier = Modifier.padding(innerPadding))
                }
            }*/

        }
    }

    @Composable
    fun SetSystemBarsAppearance(
        statusBarColor: Color,
        navBarColor: Color = statusBarColor,
        useDarkIcons: Boolean = false
    ) {
        val activity = LocalActivity.current
        val window = activity?.window
        val view = LocalView.current

        SideEffect {
            // Safe for all API levels
            @Suppress("DEPRECATION") // suppress warning, safe fallback for < API 34
            window?.statusBarColor = statusBarColor.toArgb()
            @Suppress("DEPRECATION")
            window?.navigationBarColor = navBarColor.toArgb()

            if (window != null) {
                WindowCompat.getInsetsController(window, view).apply {
                    isAppearanceLightStatusBars = useDarkIcons
                    isAppearanceLightNavigationBars = useDarkIcons
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationComposeTheme {
        Greeting("Android")
    }
}

