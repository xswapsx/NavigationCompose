@file:OptIn(ExperimentalMaterial3Api::class)

package com.appynitty.navigationcompose.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.appynitty.navigationcompose.R


@Composable
fun FirstScreenTopAppBar(openDrawer: () -> Unit){
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.first_screen)) },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(Icons.Filled.Menu, stringResource(id = R.string.open_drawer))
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SecondScreenTopAppBar(openDrawer: () -> Unit){
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.secondScreen)) },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(Icons.Filled.Menu, stringResource(id = R.string.open_drawer))
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}