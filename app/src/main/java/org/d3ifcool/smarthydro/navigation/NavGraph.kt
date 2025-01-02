package org.d3ifcool.smarthydro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.d3ifcool.smarthydro.ui.screen.HomeScreen
import org.d3ifcool.smarthydro.ui.screen.LoginScreen
import org.d3ifcool.smarthydro.ui.screen.ProfileScreen
import org.d3ifcool.smarthydro.ui.screen.RegisterScreen

@Composable
fun SetUpNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navHostController = navHostController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navHostController = navHostController)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navHostController = navHostController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navHostController = navHostController)
        }
    }
}
