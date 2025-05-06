package com.appynitty.navigationcompose.ui.second_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.appynitty.navigationcompose.NavigationActions

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    navigationActions: NavigationActions,
    userName: String
) {
    Log.d("SecondScreen====", userName)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hi, $userName! Welcome to the second screen",
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = modifier,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            navigationActions.navigateToFirstScreen()
        }) {
            Text("Go back to first screen")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecondScreenPreview() {
    SecondScreen(
        navigationActions = NavigationActions(NavHostController(LocalContext.current)),
        userName = ""
    )
}