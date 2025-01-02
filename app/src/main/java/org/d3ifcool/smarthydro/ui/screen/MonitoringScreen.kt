package org.d3ifcool.smarthydro.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

@Composable
fun MonitoringScreen() {
   Column (
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = Modifier
           .fillMaxWidth()
           .padding(top = 18.dp)
   ) {
       var status by remember { mutableStateOf(Status.KURANG_NUTRISI) }
       var statusBasahKering by remember { mutableStateOf(StatusBasahKering.KERING) }
       var kondisiPompa by remember { mutableStateOf(KondisiPompa.OFF) }

       Image(
           painter = painterResource(R.drawable.ic_logo_monitoring),
           contentDescription = null,
           modifier = Modifier.size(150.dp)
       )

       Text(
           text = stringResource(R.string.level_nutrisi),
           fontWeight = FontWeight.W500,
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 18.dp)
       )

       // text PPM
       Row (
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 18.dp),
           verticalAlignment = Alignment.CenterVertically
       ) {
           Box(
               modifier = Modifier
                   .border(BorderStroke(1.dp, Color(0XFFC7C7C7)), RoundedCornerShape(8.dp))
                   .padding(8.dp)
                   .width(100.dp),
           ) {
               Text(
                   text = "T",
                   modifier = Modifier.align(Alignment.Center),
                   color = Color.Black
               )
           }
           Spacer(modifier = Modifier.width(8.dp))
           Text(
               text = stringResource(R.string.ppm),

           )
       }

       Spacer(modifier = Modifier.height(16.dp))

       Text(
           text = "Status :",
           fontWeight = FontWeight.W500,
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 18.dp)
       )

       Row (
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.padding(horizontal = 12.dp)
       ) {

           Row (
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.clickable {
                   status = Status.KURANG_NUTRISI
               }
           ){

           Image(
               painter = if (status == Status.KURANG_NUTRISI) painterResource(R.drawable.ic_bulat_merah) else painterResource(R.drawable.ic_bulat_abu_kosong),
               contentDescription = null,
               modifier = Modifier.size(12.dp)
           )

           Spacer(modifier = Modifier.width(8.dp))

           Text(
               text = stringResource(R.string.kurang_nutrisi),
               fontSize = 12.sp
           )
           }

           Spacer(modifier = Modifier.width(16.dp))

           Row (
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.clickable {
                   status = Status.NUTRISI_TERCUKUPI
               }
           ) {
           Image(
               painter = if (status == Status.NUTRISI_TERCUKUPI) painterResource(R.drawable.ic_bulat_hijau) else painterResource(R.drawable.ic_bulat_abu_kosong),
               contentDescription = null,
               modifier = Modifier.size(12.dp)
           )

           Spacer(modifier = Modifier.width(8.dp))

           Text(
               text = stringResource(R.string.nutrisi_tercukupi),
               fontSize = 12.sp
           )
           }

           Spacer(modifier = Modifier.width(16.dp))

           Row (
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.clickable {
                   status = Status.KELEBIHAN_NUTRISI
               }
           ) {
           Image(
               painter = if (status == Status.KELEBIHAN_NUTRISI) painterResource(R.drawable.ic_bulat_merah) else painterResource(R.drawable.ic_bulat_abu_kosong),
               contentDescription = null,
               modifier = Modifier.size(12.dp)
           )

           Spacer(modifier = Modifier.width(8.dp))

           Text(
               text = stringResource(R.string.kelebihan_nutrisi),
               fontSize = 12.sp
           )
           }
       }

       Spacer(modifier = Modifier.height(16.dp))

       Text(
           text = "Status Basah/Kering",
           fontWeight = FontWeight.W500,
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 18.dp)
       )

       Row (
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 18.dp)
       ) {
           OutlinedButton (
               modifier = Modifier.weight(1f),
               onClick = {  },
               shape = RoundedCornerShape(8.dp),
               border = if (statusBasahKering == StatusBasahKering.BASAH) BorderStroke(0.dp, Color.Transparent) else BorderStroke(0.5.dp, Color.Gray),
               colors = if (statusBasahKering == StatusBasahKering.BASAH) ButtonDefaults.buttonColors(containerColor = colorResource(R.color.tosca)) else ButtonDefaults.buttonColors(containerColor = Color.White)
           ) {
               Text(
                   text = "BASAH",
                   color = if (statusBasahKering == StatusBasahKering.BASAH) Color.White else Color.Black
               )
           }

           Spacer(modifier = Modifier.width(8.dp))

           OutlinedButton (
               modifier = Modifier.weight(1f),
               onClick = {  },
               colors = if (statusBasahKering == StatusBasahKering.KERING) ButtonDefaults.buttonColors(containerColor = Color(0XFFff0000)) else ButtonDefaults.buttonColors(containerColor = Color.White),
               shape = RoundedCornerShape(8.dp),
               border = if (statusBasahKering == StatusBasahKering.KERING) BorderStroke(0.dp, Color.Transparent) else BorderStroke(0.5.dp, Color.Gray),
           ) {
               Text(
                   text = "KERING",
                   color = if (statusBasahKering == StatusBasahKering.KERING) Color.White else Color.Black
               )
           }
       }

       Spacer(modifier = Modifier.height(16.dp))

       Text(
           text = "Kondisi pompa",
           fontWeight = FontWeight.W500,
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 18.dp)
       )

       Row (
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 18.dp)
       ) {
           OutlinedButton (
               modifier = Modifier.weight(1f),
               onClick = {  },
               shape = RoundedCornerShape(8.dp),
               border = if (kondisiPompa == KondisiPompa.ON) BorderStroke(0.dp, Color.Transparent) else BorderStroke(0.5.dp, Color.Gray),
               colors = if (kondisiPompa == KondisiPompa.ON) ButtonDefaults.buttonColors(containerColor = colorResource(R.color.tosca)) else ButtonDefaults.buttonColors(containerColor = Color.White)
           ) {
               Text(
                   text = "ON",
                   color = if (kondisiPompa == KondisiPompa.ON) Color.White else Color.Black
               )
           }

           Spacer(modifier = Modifier.width(8.dp))

           OutlinedButton (
               modifier = Modifier.weight(1f),
               onClick = {  },
               colors = if (kondisiPompa == KondisiPompa.OFF) ButtonDefaults.buttonColors(containerColor = Color(0XFFff0000)) else ButtonDefaults.buttonColors(containerColor = Color.White),
               shape = RoundedCornerShape(8.dp),
               border = if (kondisiPompa == KondisiPompa.OFF) BorderStroke(0.dp, Color.Transparent) else BorderStroke(0.5.dp, Color.Gray),
           ) {
               Text(
                   text = "OFF",
                   color = if (kondisiPompa == KondisiPompa.OFF) Color.White else Color.Black
               )
           }
       }


   }
}

enum class Status {
    KURANG_NUTRISI,
    NUTRISI_TERCUKUPI,
    KELEBIHAN_NUTRISI
}
enum class StatusBasahKering {
    BASAH,
    KERING
}
enum class KondisiPompa {
    ON,
    OFF
}

@Preview
@Composable
fun MonitoringScreenPreview() {
    SmartHydroTheme {
        MonitoringScreen()
    }
}

