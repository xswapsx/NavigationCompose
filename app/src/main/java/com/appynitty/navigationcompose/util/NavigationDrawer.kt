package com.appynitty.navigationcompose.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.appynitty.navigationcompose.Destinations
import com.appynitty.navigationcompose.NavigationActions
import com.appynitty.navigationcompose.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppModalDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navActions: NavigationActions,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute,
                navigateToFirstScreen = { navActions.navigateToFirstScreen() },
                navigateToSecondScreen = { navActions.navigateToSecondScreen("Guest") },
                closeDrawer = { coroutineScope.launch { drawerState.close() } },
                scope = coroutineScope
            )
        }) {
        content()
    }
}

@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToFirstScreen: () -> Unit,
    navigateToSecondScreen: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    scope: CoroutineScope
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            DrawerHeader()
            DrawerButton(
                painter = painterResource(id = R.drawable.ic_bulb),
                label = "First Screen",
                isSelected = currentRoute == Destinations.FIRST_SCREEN_ROUTE,
                action = {
                    scope.launch {
                        closeDrawer()
                        delay(250)
                        navigateToFirstScreen()
                    }

                }
            )
            DrawerButton(
                painter = painterResource(id = R.drawable.ici_nature),
                label = "Second Screen",
                isSelected = currentRoute == Destinations.SECOND_SCREEN_ROUTE,
                action = {
                    scope.launch {
                        closeDrawer()
                        delay(250)
                        navigateToSecondScreen()
                    }
                }
            )
        }
    }
}

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .height(dimensionResource(R.dimen.header_height))
            .padding(dimensionResource(R.dimen.header_padding))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = stringResource(id = R.string.header_image_content_description),
            modifier = Modifier.width(dimensionResource(id = R.dimen.header_image_width))
        )
        Text(text = stringResource(R.string.app_name), color = MaterialTheme.colorScheme.surface)
    }
}

@Composable
fun DrawerButton(
    painter: Painter,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tintColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    }

    TextButton(
        onClick = action,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(painter = painter, contentDescription = null, tint = tintColor)
            Spacer(Modifier.width(16.dp))
            Text(text = label, style = MaterialTheme.typography.bodySmall, color = tintColor)
        }
    }
}