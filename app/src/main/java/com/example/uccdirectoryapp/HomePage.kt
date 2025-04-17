package com.example.uccdirectoryapp

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00205B))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            // UCC Logo
            Image(
                painter = painterResource(id = R.drawable.ucc_logo),
                contentDescription = "UCC Logo"
            )

            // Welcome Text
            Text(
                "Welcome to UCC Directory App",
                fontSize = 24.sp,
                color = Color.White
            )

            // Navigation Buttons
            val buttonColor = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC72C))
            Button(onClick = { navController.navigate("faculty_directory") }, colors = buttonColor) {
                Text("Faculty/Staff Directory", color = Color.Black)
            }
            Button(onClick = { navController.navigate("courses") }, colors = buttonColor) {
                Text("Courses", color = Color.Black)
            }
            Button(onClick = { navController.navigate("admissions") }, colors = buttonColor) {
                Text("Admissions", color = Color.Black)
            }
            Button(onClick = { navController.navigate("social_media") }, colors = buttonColor) {
                Text("Social Media", color = Color.Black)
            }
        }

        // FAB for Emailing HODs
        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFFFFC72C)
        ) {
            Icon(imageVector = Icons.Default.Email, contentDescription = "Email HOD", tint = Color.Black)
        }

        // Dialog with Department Dropdown
        if (showDialog) {
            EmailHODDialog(onDismiss = { showDialog = false }) { recipientEmail, senderEmail, message ->
                Toast.makeText(
                    context,
                    "Email sent to $recipientEmail from $senderEmail\n\n$message",
                    Toast.LENGTH_LONG
                ).show()
                showDialog = false
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailHODDialog(
    onDismiss: () -> Unit,
    onSend: (String, String, String) -> Unit
) {
    val departmentToEmail = mapOf(
        "School of Technology" to "hod.technology@ucc.edu",
        "School of Arts" to "hod.arts@ucc.edu",
        "School of Education" to "hod.education@ucc.edu",
        "School of Nursing" to "hod.nursing@ucc.edu",
        "School of Engineering" to "hod.engineering@ucc.edu",
        "School of Law" to "hod.law@ucc.edu",
        "School of Agriculture" to "hod.agriculture@ucc.edu",
        "School of Architecture" to "hod.architecture@ucc.edu",
        "School of Business" to "hod.business@ucc.edu"
    )

    val departments = departmentToEmail.keys.toList()
    var expanded by remember { mutableStateOf(false) }
    var selectedDepartment by remember { mutableStateOf(departments.first()) }
    var senderEmail by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (senderEmail.isNotBlank() && message.isNotBlank()) {
                        val recipientEmail = departmentToEmail[selectedDepartment] ?: return@Button
                        onSend(recipientEmail, senderEmail, message)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC72C))
            ) {
                Text("Send", color = Color.Black)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.Gray)
            }
        },
        title = { Text("Email Head of Department") },
        text = {
            Column {
                // Dropdown for Departments
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = selectedDepartment,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Select Department") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        departments.forEach { department ->
                            DropdownMenuItem(
                                text = { Text(department) },
                                onClick = {
                                    selectedDepartment = department
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = senderEmail,
                    onValueChange = { senderEmail = it },
                    label = { Text("Your School Email") },
                    placeholder = { Text("example@stu.ucc.edu.gh") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Your Message") },
                    placeholder = { Text("Write your inquiry...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }
        }
    )
}

