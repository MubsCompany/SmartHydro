package org.d3ifcool.smarthydro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.d3ifcool.smarthydro.navigation.SetupNavGraph
import org.d3ifcool.smarthydro.ui.screen.AppEntry
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SmartHydroTheme {
                SetupNavGraph(navHostController = navController, context = this)
            }
        }
    }
}


