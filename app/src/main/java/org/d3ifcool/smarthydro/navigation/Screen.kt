package org.d3ifcool.smarthydro.navigation

sealed class Screen (val route: String) {

    data object Home: Screen("homeScreen")

    data object Profile: Screen("profileScreen")

    data object Login: Screen("loginScreen")

    data object Register: Screen("registerScreen")
}