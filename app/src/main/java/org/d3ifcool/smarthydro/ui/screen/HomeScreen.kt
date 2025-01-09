package org.d3ifcool.smarthydro.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.navigation.Screen

import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val context = LocalContext.current // Dapatkan context lokal

    Scaffold(
        topBar = {
            AppBarHome(
                label = stringResource(R.string.home),
                navHostController = navHostController,
                context = context // Berikan context ke AppBarHome
            )
        }
    ) { paddingValues ->
        HomeScreenContent(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        )
    }
}



@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    HomeTabBar(modifier = modifier)
}

@Composable
fun HomeTabBar(modifier: Modifier = Modifier) {
    val tabs = listOf("Kontroling", "Monitoring")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column (
        modifier = modifier
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) },
                    selectedContentColor = colorResource(R.color.tosca),
                )
            }
        }

        // Konten berdasarkan tab yang dipilih
        when (selectedTabIndex) {
            0 -> KontrolingContent()
            1 -> MonitoringContent()
        }
    }
}

@Composable
fun KontrolingContent() {
    KontrolingScreen()
}

@Composable
fun MonitoringContent() {
    MonitoringScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarHome(
    label: String,
    navHostController: NavHostController,
    context: Context, // Tambahkan parameter context
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = label) },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Profile",
                    modifier = Modifier.size(32.dp)
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        navHostController.navigate(Screen.Profile.route)
                        expanded = false
                    },
                    text = {
                        Text("Profile")
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            UserPreferences.setLoginStatus(context, false) // Ubah status login
                        }
                        navHostController.navigate(Screen.Login.route) { // Arahkan ke layar Login
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                        expanded = false
                    },
                    text = {
                        Text("Logout")
                    }
                )
            }
        }
    )
}
