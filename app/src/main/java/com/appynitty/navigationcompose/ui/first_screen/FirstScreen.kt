package com.appynitty.navigationcompose.ui.first_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.appynitty.navigationcompose.util.FirstScreenTopAppBar

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    navigationActions: NavigationActions,
    openDrawer: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { FirstScreenTopAppBar(openDrawer = openDrawer) }
    ) { paddingValues ->
        var userName by remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Please enter your name:",
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = modifier,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Name") }
            )

            Spacer(modifier.height(15.dp))

            Button(onClick = {
                navigationActions.navigateToSecondScreen(userName)
            }) {
                Text("Go to second screen")
            }

        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FirstScreenPreview() {
    FirstScreen(
        navigationActions = NavigationActions(NavHostController(LocalContext.current)),
        openDrawer = {})
}