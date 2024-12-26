package org.d3ifcool.smarthydro.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

@Composable
fun ProfileScreen() {
    Scaffold (
        topBar = { AppBarProfile(label = stringResource(R.string.profile)) }
    ) { paddingValues ->
        ProfileScreenContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(top = 120.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_telkom),
                contentDescription = "Logo Telkom University",
                modifier = Modifier.size(150.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_logo_desa),
                contentDescription = "Logo Telkom University",
                modifier = Modifier.size(138.dp)
            )
        }

        Text(
            text = stringResource(R.string.alamat_telkom),
            modifier = Modifier.padding(32.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarProfile(label: String) {
    TopAppBar(
        title = { Text(text = label) },
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = "Profile",
                    modifier = Modifier.size(32.dp)
                )
            }
        }

    )
}

@Preview
@Composable
fun ProfileScreenPreview() {
    SmartHydroTheme {
        ProfileScreen()
    }
}