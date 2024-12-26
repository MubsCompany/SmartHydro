package org.d3ifcool.smarthydro.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

@Composable
fun MonitoringScreen() {
   Column (
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = Modifier.fillMaxWidth()
   ) {
       Image(
           painter = painterResource(R.drawable.ic_logo_monitoring),
           contentDescription = null,
           modifier = Modifier.size(150.dp)
       )

       Text(
           text = "Level Nutrisi",
           fontWeight = FontWeight.W500,
           modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp)
       )

       // text PPM
       Row (
           modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp)
       ) {
           Text(
               text = stringResource(R.string.tanda_tanya)
           )
           Text(
               text = stringResource(R.string.ppm),

           )
       }

       Text(
           text = "Status :",
           modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp)
       )

       Row (
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.padding(horizontal = 12.dp)
       ) {
           Image(
               painter = painterResource(R.drawable.ic_bulat_tosca),
               contentDescription = null,
               modifier = Modifier.size(12.dp)
           )

           Spacer(modifier = Modifier.width(8.dp))

           Text(
               text = stringResource(R.string.kurang_nutrisi),
               fontSize = 12.sp
           )

           Spacer(modifier = Modifier.width(16.dp))

           Image(
               painter = painterResource(R.drawable.ic_bulat_abu_kosong),
               contentDescription = null,
               modifier = Modifier.size(12.dp)
           )

           Spacer(modifier = Modifier.width(8.dp))

           Text(
               text = stringResource(R.string.nutrisi_tercukupi),
               fontSize = 12.sp
           )

           Spacer(modifier = Modifier.width(16.dp))

           Image(
               painter = painterResource(R.drawable.ic_bulat_abu_kosong),
               contentDescription = null,
               modifier = Modifier.size(12.dp)
           )

           Spacer(modifier = Modifier.width(8.dp))

           Text(
               text = stringResource(R.string.kelebihan_nutrisi),
               fontSize = 12.sp
           )
       }

       Text(
           text = "Status Basah/Kering",
           fontWeight = FontWeight.W500,
           modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp)
       )

       Row (
           modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp)
       ) {
           OutlinedButton (
               onClick = {  },
               shape = RoundedCornerShape(8.dp),
               border = BorderStroke(0.dp, Color.Transparent),
               colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.tosca))
           ) {
               Text(text = "Basah", color = Color.White)
           }
           OutlinedButton (
               onClick = {  },
               colors = ButtonDefaults.buttonColors(containerColor = Color.White),
               shape = RoundedCornerShape(8.dp),
           ) {
               Text(text = "Kering", color = Color.Black)
           }
       }

       Text(
           text = "Kondisi pompa",
           fontWeight = FontWeight.W500,
           modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp)
       )

       Row (
           modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp)
       ) {
           OutlinedButton (
               onClick = {  },
               shape = RoundedCornerShape(8.dp),
               border = BorderStroke(0.dp, Color.Transparent),
               colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.tosca))
           ) {
               Text(text = "On", color = Color.White)
           }
           OutlinedButton (
               onClick = {  },
               colors = ButtonDefaults.buttonColors(containerColor = Color.White),
               shape = RoundedCornerShape(8.dp),
           ) {
               Text(text = "Off", color = Color.Black)
           }
       }


   }
}


@Preview
@Composable
fun MonitoringScreenPreview() {
    SmartHydroTheme {
        MonitoringScreen()
    }
}

