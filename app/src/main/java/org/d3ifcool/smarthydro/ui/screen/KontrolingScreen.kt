package org.d3ifcool.smarthydro.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

@Composable
fun KontrolingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Gambar
        Image(
            painter = painterResource(id = R.drawable.ic_logo_kontroling), // Ganti dengan ikon yang sesuai
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input Nutrisi
        OutlinedTextField(
            value = remember { TextFieldValue("") },
            onValueChange = {},
            label = { Text("Input Nutrisi") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Text("PPM")
            }
        )


        Spacer(modifier = Modifier.height(16.dp))

        // Input Waktu
        Text(
            text = stringResource(R.string.input_waktu),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TimeInput()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Simpan
        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.tosca))
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun TimeInput() {
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "On"
            )

            Spacer(modifier = Modifier.width(32.dp))

            OutlinedTextField(
                value = remember { TextFieldValue("") },
                onValueChange = {},
                modifier = Modifier.size(36.dp)
            )

            Text(text = "  :  ")

            OutlinedTextField(
                value = remember { TextFieldValue("") },
                onValueChange = {},
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(32.dp))

            Image(
                painter = painterResource(R.drawable.ic_bulat_tosca),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "AM"
            )

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(R.drawable.ic_bulat_abu_kosong),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "PM"
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "On"
            )

            Spacer(modifier = Modifier.width(32.dp))

            OutlinedTextField(
                value = remember { TextFieldValue("") },
                onValueChange = {},
                modifier = Modifier.size(36.dp)
            )

            Text(text = "  :  ")

            OutlinedTextField(
                value = remember { TextFieldValue("") },
                onValueChange = {},
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(32.dp))

            Image(
                painter = painterResource(R.drawable.ic_bulat_tosca),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "AM"
            )

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(R.drawable.ic_bulat_abu_kosong),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "PM"
            )

        }

    }

}

@Preview
@Composable
fun KontrolingScreenPreview() {
    SmartHydroTheme {
        KontrolingScreen()
    }
}