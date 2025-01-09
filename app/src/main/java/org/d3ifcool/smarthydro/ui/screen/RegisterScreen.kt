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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    var username by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.padding(16.dp)
    ) {

        AppBarLoginRegister()

        Text(
            text = "Register",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nama") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("No.HP") },
            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (username.isBlank() || phone.isBlank() || email.isBlank() || password.isBlank()) {
                    errorMessage = "Semua bidang harus diisi!"
                    return@Button
                }
                isLoading = true
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        isLoading = false
                        if (task.isSuccessful) {
                            val user = FirebaseAuth.getInstance().currentUser
                            val userId = user?.uid
                            val userData = mapOf(
                                "username" to username,
                                "phone" to phone,
                                "email" to email
                            )

                            FirebaseFirestore.getInstance()
                                .collection("users")
                                .document(userId ?: "")
                                .set(userData)
                                .addOnSuccessListener {
                                    navHostController.navigate(Screen.Home.route)
                                }
                                .addOnFailureListener { e ->
                                    errorMessage = "Gagal menyimpan data pengguna: ${e.localizedMessage}"
                                }
                        } else {
                            errorMessage = "Gagal registrasi: ${task.exception?.localizedMessage}"
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.tosca))
        ) {
            Text(text = "Kirim", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(text = "Sudah punya akun? ", color = Color.Gray)
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
