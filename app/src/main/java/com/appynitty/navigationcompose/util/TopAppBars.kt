@file:OptIn(ExperimentalMaterial3Api::class)

package com.appynitty.navigationcompose.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.appynitty.navigationcompose.R


@Composable
fun FirstScreenTopAppBar(openDrawer: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.first_screen)) },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(Icons.Filled.Menu, stringResource(id = R.string.open_drawer))
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White, // Background color
            titleContentColor = MaterialTheme.colorScheme.onBackground, // Title text color
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground // Icon color
        )
    )
}

@Composable
fun SecondScreenTopAppBar(
    openDrawer: () -> Unit,
    onClickSettings: () -> Unit,
    onClickAboutUs: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.secondScreen)) },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(Icons.Filled.Menu, stringResource(id = R.string.open_drawer))
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White, // Background color
            titleContentColor = MaterialTheme.colorScheme.onBackground, // Title text color
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground // Icon color
        ),
        actions = { MainMenu(onClickSettings, onClickAboutUs) }
    )
}

@Composable
private fun MainMenu(onClickSettings: () -> Unit, onClickAboutUs: () -> Unit) {
    TopAppBarDropDownMenu(iconContent = {
        Icon(
            Icons.Filled.MoreVert,
            null, tint = Color.White
        )
    }) { closeMenu ->
        DropdownMenuItem(
            text = { Text(text = "Settings") },
            onClick = { onClickSettings(); closeMenu() }
        )

        DropdownMenuItem(
            text = { Text("About us") },
            onClick = { onClickAboutUs(); closeMenu() }
        )
    }
}

@Composable
private fun TopAppBarDropDownMenu(
    iconContent: @Composable () -> Unit,
    content: @Composable ColumnScope. (() -> Unit) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = !expanded }) {
            iconContent()
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize(Alignment.TopEnd)
        ) {
            content { expanded = !expanded }
        }
    }
}