package org.d3ifcool.smarthydro.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.d3ifcool.smarthydro.ui.screen.HomeScreen
import org.d3ifcool.smarthydro.ui.screen.LoginScreen
import org.d3ifcool.smarthydro.ui.screen.ProfileScreen
import org.d3ifcool.smarthydro.ui.screen.RegisterScreen
import org.d3ifcool.smarthydro.ui.screen.UserPreferences

@Composable
fun SetupNavGraph(navHostController: NavHostController, context: Context) {
    val isLoggedIn = UserPreferences.getLoginStatus(context).collectAsState(initial = false)

    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn.value) Screen.Home.route else Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navHostController = navHostController, context = context)
        }
        composable(Screen.Home.route) {
            HomeScreen(navHostController = navHostController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navHostController = navHostController)
        }
    }
}

