package org.d3ifcool.smarthydro.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.navigation.Screen
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarHomeProfile(
    label: String,
    navHostController: NavHostController,
    ) {
        TopAppBar(
        title = { Text(text = label) },
            navigationIcon = {
                IconButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_profile),
                        contentDescription = "Profile",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

    )
}
//
//@Preview
//@Composable
//fun AppBarHomeProfilePreview() {
//    SmartHydroTheme {
//        AppBarHomeProfile("Test")
//    }
//}