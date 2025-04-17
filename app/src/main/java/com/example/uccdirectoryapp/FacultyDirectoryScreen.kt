package com.example.uccdirectoryapp

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

@Composable
fun FacultyDirectoryScreen() {
    val facultyList = listOf(
        Faculty(
            name = "John Brown",
            faculty = "School of Technology",
            phone = "1876-456-7890",
            email = "john.brown@ucc.edu",
            photoResId = R.drawable.lecturer2
        ),
        Faculty(
            name = "Grace Hamilton",
            faculty = "School of Arts",
            phone = "876-888-7777",
            email = "grace.hamilton@ucc.edu",
            photoResId = R.drawable.lectuer1

        ),
        Faculty(
            name = "Mikel Johnson",
            faculty = "School of Education",
            phone = "876-111-2222",
            email = "Mikel.Johnson@ucc.edu",
            photoResId = R.drawable.lecturer10
        ),
        Faculty(
            name = "Linda White",
            faculty = "School of Nursing",
            phone = "876-654-0987",
            email = "linda.white@ucc.edu",
            photoResId = R.drawable.lecturer9
        ),
        Faculty(
            name = "Robert Black",
            faculty = "School of Engineering",
            phone = "876-333-4444",
            email = "robert.black@ucc.edu",
            photoResId = R.drawable.lecturer3
        ),
        Faculty(
            name = "Patricia Black",
            faculty = "School of Law",
            phone = "876-876-5784",
            email = "patricia.black@ucc.edu",
            photoResId = R.drawable.lecturer8
        ),
        Faculty(
            name = "Emily Taylor",
            faculty = "School of Agriculture",
            phone = "876-888-8795",
            email = "emily.taylor@ucc.edu",
            photoResId = R.drawable.lecturer7

        ),
        Faculty(
            name = "James Williams",
            faculty = "School of Architecture",
            phone = "876-6363-7854",
            email = "james.williams@ucc.edu",
            photoResId = R.drawable.lecturer5
        ),
        Faculty(
            name = "Karl Anderson",
            faculty = "School of Business",
            phone = "876-874-4565",
            email = "karl.anderson@ucc.edu",
            photoResId = R.drawable.lecturer4
        ),
        Faculty(
            name = "Jackie Christie",
            faculty = "School of Business",
            phone = "876-654-3310",
            email = "jackie.christie@ucc.edu",
            photoResId = R.drawable.lecturer6
        )
    )


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00205B))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {
        item {
            Text("Faculty Directory", fontSize = 24.sp, modifier = Modifier.padding(bottom = 8.dp))

        }

        items(facultyList) { faculty ->
            FacultyCard(faculty)
        }
    }
}

@Composable
fun FacultyCard(faculty: Faculty) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = faculty.photoResId),
                contentDescription = "${faculty.name}'s photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(faculty.name, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(faculty.faculty, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(4.dp))
                Text(faculty.phone, fontSize = 14.sp, color = Color.Blue, modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_DIAL, "tel:${faculty.phone}".toUri())
                    context.startActivity(intent)
                })
                Text(faculty.email, fontSize = 14.sp, color = Color.Blue, modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:${faculty.email}".toUri()
                        putExtra(Intent.EXTRA_SUBJECT, "Faculty Inquiry")
                    }
                    context.startActivity(intent)
                })
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, "tel:${faculty.phone}".toUri())
                    context.startActivity(intent)
                }) {
                    Icon(Icons.Filled.Call, contentDescription = "Call")
                }
                IconButton(onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:${faculty.email}".toUri()
                        putExtra(Intent.EXTRA_SUBJECT, "Faculty Inquiry")
                    }
                    context.startActivity(intent)
                }) {
                    Icon(Icons.Filled.Email, contentDescription = "Email")
                }
            }
        }
    }
}

data class Faculty(
    val name: String,
    val faculty: String,
    val phone: String,
    val email: String,
    val photoResId: Int
)
