package org.d3ifcool.smarthydro.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import org.d3ifcool.smarthydro.ui.AppBarLoginRegister
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.navigation.Screen
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    ) {
    Scaffold { innerPadding ->

        LoginScreenContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            navHostController = navHostController
        )
    }
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.padding(16.dp)
    ) {

        AppBarLoginRegister()

        Row(
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(top = 48.dp),
                    text = stringResource(R.string.selamat_datang_di_smarthydro),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.army),
                    fontSize = 24.sp
                )
                Text(
                    modifier = Modifier.padding(top = 1.dp),
                    text = stringResource(R.string.desa_cibiru_wetan),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.army),
                    fontSize = 24.sp
                )
            }

            Image(
                painter = painterResource(R.drawable.ic_logo_aplikasi),
                contentDescription = "Logo Aplikasi",
                modifier = Modifier.size(200.dp)
            )
        }

//        Text(
//            text = stringResource(R.string.login),
//            modifier = Modifier
//                .size(32.dp)
//                .fillMaxWidth()
//        )

        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )

        // Username Field
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle username change */ },
            label = { Text("Username") },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Username Icon")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle password change */ },
            label = { Text("Password") },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password Icon")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password
        Text(
            text = "Forgot password?",
            color = colorResource(R.color.tosca),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /* Handle forgot password */ }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = { 
                navHostController.navigate(Screen.Home.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.tosca))
        ) {
            Text(text = "Kirim", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Register Link
        Row {

            Text(
                text = "Belum punya akun? ",
                color = Color.Gray,
                modifier = Modifier.clickable { /* Handle register */ }
            )
            Text(
                text = "Register",
                color = colorResource(R.color.tosca),
                modifier = Modifier.clickable {
                    navHostController.navigate(Screen.Register.route)
                },
                fontWeight = FontWeight.W500
            )
        }

    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    SmartHydroTheme {
        LoginScreen(navHostController = rememberNavController())
    }
}
