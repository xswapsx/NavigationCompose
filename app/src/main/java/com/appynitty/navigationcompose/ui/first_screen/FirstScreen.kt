package com.appynitty.navigationcompose.ui.first_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.appynitty.navigationcompose.Destinations

@Composable
fun FirstScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "This is first screen",
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier.height(15.dp))

        Button(onClick = {
            navController.navigate(Destinations.SECOND_SCREEN_ROUTE)
        }) {
            Text("Go to second screen")
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FirstScreenPreview() {
    FirstScreen(navController = NavHostController(LocalContext.current))
}