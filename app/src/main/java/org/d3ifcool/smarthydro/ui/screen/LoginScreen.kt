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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.navigation.Screen
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme
import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.d3ifcool.smarthydro.navigation.SetupNavGraph

val Context.dataStore by preferencesDataStore("user_prefs")

object UserPreferences {
    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    fun getLoginStatus(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }
    }

    suspend fun setLoginStatus(context: Context, isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }
}


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    context: Context // Tambahkan parameter ini
) {
    Scaffold { innerPadding ->
        LoginScreenContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            navHostController = navHostController,
            context = context // Berikan context
        )
    }
}


@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    context: Context
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

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
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )

        // Username Field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
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
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            shape = RoundedCornerShape(8.dp),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password Icon")
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = {
                isLoading = true
                FirebaseFirestore.getInstance()
                    .collection("users")
                    .whereEqualTo("username", username)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val email = querySnapshot.documents[0].getString("email")
                            FirebaseAuth.getInstance()
                                .signInWithEmailAndPassword(email ?: "", password)
                                .addOnCompleteListener { task ->
                                    isLoading = false
                                    if (task.isSuccessful) {
                                        // Simpan status login
                                        CoroutineScope(Dispatchers.IO).launch {
                                            UserPreferences.setLoginStatus(context, true)
                                        }
                                        navHostController.navigate(Screen.Home.route) {
                                            popUpTo(Screen.Login.route) { inclusive = true }
                                        }
                                    } else {
                                        val errorCode = task.exception?.message
                                        errorMessage = if (errorCode?.contains("password") == true) {
                                            "Password salah."
                                        } else {
                                            "Login gagal: Password Salah"
                                        }
                                    }
                                }
                        } else {
                            isLoading = false
                            errorMessage = "Username tidak ditemukan."
                        }
                    }
                    .addOnFailureListener { e ->
                        isLoading = false
                        errorMessage = "Kesalahan saat mengambil data: ${e.localizedMessage}"
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
            Text(
                text = "Belum punya akun? ",
                color = Color.Gray
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
@Composable
fun CheckUserLogin(navHostController: NavHostController, context: Context) {
    val isLoggedInFlow = UserPreferences.getLoginStatus(context).collectAsState(initial = false)

    if (isLoggedInFlow.value) {
        // Jika pengguna sudah login, arahkan ke layar Home
        navHostController.navigate(Screen.Home.route) {
            popUpTo(Screen.Login.route) { inclusive = true }
        }
    } else {
        // Jika pengguna belum login, arahkan ke layar Login
        navHostController.navigate(Screen.Login.route) {
            popUpTo(Screen.Login.route) { inclusive = true }
        }
    }
}



@Composable
fun AppEntry(navHostController: NavHostController, context: Context) {
    CheckUserLogin(navHostController = navHostController, context = context)
}




