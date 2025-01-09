package org.d3ifcool.smarthydro.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction

@Composable
fun KontrolingScreen() {
    val nutrisiValue = remember { mutableStateOf("") }
    val waktuOnJam = remember { mutableStateOf("") }
    val waktuOnMenit = remember { mutableStateOf("") }
    val waktuOffJam = remember { mutableStateOf("") }
    val waktuOffMenit = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = remember { mutableStateOf<String?>(null) }

    // Focus Requesters
    val waktuOnJamFocusRequester = remember { FocusRequester() }

    LaunchedEffect(snackbarMessage.value) {
        snackbarMessage.value?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage.value = null // Reset message to avoid repeated calls
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SnackbarHost(hostState = snackbarHostState)

        Image(
            painter = painterResource(id = R.drawable.ic_logo_kontroling),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nutrisiValue.value,
            onValueChange = { nutrisiValue.value = it },
            label = { Text("Input Nutrisi") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = { Text("PPM") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.input_waktu),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TimeInput(waktuOnJam, waktuOnMenit, "On")
        Spacer(modifier = Modifier.height(16.dp))
        TimeInput(waktuOffJam, waktuOffMenit, "Off")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val waktuOn = "${waktuOnJam.value}:${waktuOnMenit.value}"
                val waktuOff = "${waktuOffJam.value}:${waktuOffMenit.value}"

                if (nutrisiValue.value.isNotEmpty() &&
                    waktuOnJam.value.isNotEmpty() && waktuOnMenit.value.isNotEmpty() &&
                    waktuOffJam.value.isNotEmpty() && waktuOffMenit.value.isNotEmpty()
                ) {
                    saveToFirebase(
                        nutrisiValue.value,
                        waktuOn,
                        waktuOff,
                        onSuccess = {
                            nutrisiValue.value = ""
                            waktuOnJam.value = ""
                            waktuOnMenit.value = ""
                            waktuOffJam.value = ""
                            waktuOffMenit.value = ""
                            snackbarMessage.value = "Data berhasil disimpan!"
                        },
                        onFailure = { error ->
                            snackbarMessage.value = "Gagal menyimpan data: $error"
                        }
                    )
                } else {
                    snackbarMessage.value = "Semua field harus diisi!"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.tosca))
        ) {
            Text("Simpan")
        }
    }
}

fun saveToFirebase(
    nutrisi: String,
    waktuOn: String,
    waktuOff: String,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit
) {
    val database = Firebase.database.reference

    val relayData = mapOf(
        "waktuOn" to waktuOn,
        "waktuOff" to waktuOff
    )

    database.child("sensorrelay").child("status").updateChildren(relayData)
        .addOnSuccessListener {
            val tdsData = mapOf("targetNutrisi" to nutrisi)

            database.child("sensortds").child("status").updateChildren(tdsData)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    onFailure(e.message ?: "Error tidak diketahui")
                }
        }
        .addOnFailureListener { e ->
            onFailure(e.message ?: "Error tidak diketahui")
        }
}


@Composable
fun TimeInput(
    jamState: MutableState<String>,
    menitState: MutableState<String>,
    label: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label
        )

        Spacer(modifier = Modifier.width(32.dp))

        // Input Jam
        OutlinedTextField(
            value = jamState.value,
            onValueChange = { value ->
                if (value.length <= 2 && value.all { it.isDigit() }) {
                    val intValue = value.toIntOrNull()
                    if (intValue == null || (intValue in 0..23)) {
                        jamState.value = value
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.size(55.dp),
        )

        Text(text = " : ")

        // Input Menit
        OutlinedTextField(
            value = menitState.value,
            onValueChange = { value ->
                if (value.length <= 2 && value.all { it.isDigit() }) {
                    val intValue = value.toIntOrNull()
                    if (intValue == null || (intValue in 0..59)) {
                        menitState.value = value
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.size(55.dp),
        )
    }
}

@Preview
@Composable
fun KontrolingScreenPreview() {
    SmartHydroTheme {
        KontrolingScreen()
    }
}