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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    ) {
    Scaffold { innerPadding ->
        RegisterScreenContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            navHostController = navHostController
        )
    }
}

@Composable
fun RegisterScreenContent(
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

        Text(
            text = "Register",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )

        TextFieldWithIcon(
            label = "Nama",
            icon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithIcon(
            label = "No.HP",
            icon = Icons.Default.Phone
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithIcon(
            label = "Email",
            icon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldWithIcon(
            label = "Password",
            icon = Icons.Default.Lock
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Register Button
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
                text = "Sudah punya akun? ",
                color = Color.Gray,
            )
            Text(
                text = "Login",
                color = colorResource(R.color.tosca),
                modifier = Modifier.clickable { navHostController.navigate(Screen.Login.route) },
                fontWeight = FontWeight.W500
            )
        }

    }
}

@Composable
fun TextFieldWithIcon(label: String, icon: ImageVector) {
    OutlinedTextField(
        value = "",
        onValueChange = {  },
        label = { Text(label) },
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(icon, contentDescription = null)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun RegisterScreenPreview() {
    SmartHydroTheme {
        RegisterScreen(Modifier, rememberNavController())
    }
}
