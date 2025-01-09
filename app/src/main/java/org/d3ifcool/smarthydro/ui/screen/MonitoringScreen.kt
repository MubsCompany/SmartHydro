package org.d3ifcool.smarthydro.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.*
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

@Composable
fun MonitoringScreen() {
    // Referensi ke Firebase Realtime Database
    val database = FirebaseDatabase.getInstance().reference

    // State untuk memegang data yang diambil dari Realtime Database
    var status by remember { mutableStateOf(Status.KURANG_NUTRISI) }
    var statusBasahKering by remember { mutableStateOf(StatusBasahKering.KERING) }
    var kondisiPompa by remember { mutableStateOf(KondisiPompa.OFF) }
    var ppm by remember { mutableStateOf(0) }

    // Listener untuk mengambil data secara real-time
    DisposableEffect(Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                kondisiPompa = when (snapshot.child("sensorrelay/status/wet").getValue(String::class.java)) {
                    "ON" -> KondisiPompa.ON
                    "OFF" -> KondisiPompa.OFF
                    else -> KondisiPompa.OFF
                }

                ppm = snapshot.child("sensortds/status/tdsValue").getValue(Int::class.java) ?: 0

                status = when {
                    ppm < 10 -> Status.KURANG_NUTRISI
                    ppm in 11..200 -> Status.NUTRISI_TERCUKUPI
                    else -> Status.KELEBIHAN_NUTRISI
                }

                statusBasahKering = when (snapshot.child("sensorwaterlevel/status/wet").getValue(String::class.java)) {
                    "BASAH" -> StatusBasahKering.BASAH
                    "KERING" -> StatusBasahKering.KERING
                    else -> StatusBasahKering.KERING
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Log error jika data gagal diambil
                println("Database Error: ${error.message}")
            }
        }
        database.addValueEventListener(listener)
        onDispose {
            database.removeEventListener(listener)
        }
    }


    // UI
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Logo Monitoring
        Image(
            painter = painterResource(R.drawable.ic_logo_monitoring),
            contentDescription = null,
            modifier = Modifier.size(150.dp).align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Level Nutrisi dan PPM
        Text(
            text = stringResource(R.string.level_nutrisi),
            fontWeight = FontWeight.W500,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .width(100.dp),
            ) {
                Text(
                    text = ppm.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.ppm))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Status Nutrisi
        Text(
            text = "Status :",
            fontWeight = FontWeight.W500,
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            MonitoringStatusIndicator(
                isActive = status == Status.KURANG_NUTRISI,
                text = stringResource(R.string.kurang_nutrisi),
                colorActive = R.drawable.ic_bulat_merah
            )
            Spacer(modifier = Modifier.width(16.dp))
            MonitoringStatusIndicator(
                isActive = status == Status.NUTRISI_TERCUKUPI,
                text = stringResource(R.string.nutrisi_tercukupi),
                colorActive = R.drawable.ic_bulat_hijau
            )
            Spacer(modifier = Modifier.width(16.dp))
            MonitoringStatusIndicator(
                isActive = status == Status.KELEBIHAN_NUTRISI,
                text = stringResource(R.string.kelebihan_nutrisi),
                colorActive = R.drawable.ic_bulat_merah
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Status Basah/Kering
        Text(
            text = "Status Basah/Kering",
            fontWeight = FontWeight.W500,
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f),
                colors = if (statusBasahKering == StatusBasahKering.BASAH) ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.tosca)
                ) else ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(
                    if (statusBasahKering == StatusBasahKering.BASAH) 0.dp else 0.5.dp,
                    Color.Gray
                )
            ) {
                Text(
                    text = "BASAH",
                    color = if (statusBasahKering == StatusBasahKering.BASAH) Color.White else Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f),
                colors = if (statusBasahKering == StatusBasahKering.KERING) ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ) else ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(
                    if (statusBasahKering == StatusBasahKering.KERING) 0.dp else 0.5.dp,
                    Color.Gray
                )
            ) {
                Text(
                    text = "KERING",
                    color = if (statusBasahKering == StatusBasahKering.KERING) Color.White else Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Kondisi Pompa
        Text(
            text = "Kondisi Pompa",
            fontWeight = FontWeight.W500,
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f),
                colors = if (kondisiPompa == KondisiPompa.ON) ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.tosca)
                ) else ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(
                    if (kondisiPompa == KondisiPompa.ON) 0.dp else 0.5.dp,
                    Color.Gray
                )
            ) {
                Text(
                    text = "ON",
                    color = if (kondisiPompa == KondisiPompa.ON) Color.White else Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f),
                colors = if (kondisiPompa == KondisiPompa.OFF) ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ) else ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(
                    if (kondisiPompa == KondisiPompa.OFF) 0.dp else 0.5.dp,
                    Color.Gray
                )
            ) {
                Text(
                    text = "OFF",
                    color = if (kondisiPompa == KondisiPompa.OFF) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun MonitoringStatusIndicator(
    isActive: Boolean,
    text: String,
    colorActive: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(if (isActive) colorActive else R.drawable.ic_bulat_abu_kosong),
            contentDescription = null,
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 12.sp)
    }
}

enum class Status { KURANG_NUTRISI, NUTRISI_TERCUKUPI, KELEBIHAN_NUTRISI }
enum class StatusBasahKering { BASAH, KERING }
enum class KondisiPompa { ON, OFF }

@Preview
@Composable
fun MonitoringScreenPreview() {
    SmartHydroTheme {
        MonitoringScreen()
    }
}
