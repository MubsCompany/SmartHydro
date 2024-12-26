package org.d3ifcool.smarthydro.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3ifcool.smarthydro.R
import org.d3ifcool.smarthydro.ui.theme.SmartHydroTheme

@Composable
fun AppBarLoginRegister() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
                painter = painterResource(id = R.drawable.ic_logo_telkom),
                contentDescription = "Logo Telkom University",
                modifier = Modifier.size(84.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_logo_desa),
                contentDescription = "Logo Telkom University",
                modifier = Modifier.size(72.dp)
            )
    }
}

@Preview
@Composable
fun AppBarLoginRegisterPreview() {
    SmartHydroTheme {
        AppBarLoginRegister()
    }
}