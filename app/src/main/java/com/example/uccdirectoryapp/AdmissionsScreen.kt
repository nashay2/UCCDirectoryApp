package com.example.uccdirectoryapp

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

@Composable
fun AdmissionsScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00205B)) // Deep Blue Background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Admissions Information",
            fontSize = 24.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Admission Requirements: ...",
            fontSize = 16.sp,
            color = Color.White
        )
        Text(
            "For more details, please visit the UCC Admissions page.",
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, "https://www.ucc.edu.jm".toUri())
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC72C)) // UCC Yellow
        ) {
            Text("Visit General Admissions Page", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, "https://www.ucc.edu.jm/programmes/tm/bsc-in-information-technology".toUri())
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC72C)) // UCC Yellow
        ) {
            Text("IT Department Admission Requirements", color = Color.Black)
        }
    }
}

