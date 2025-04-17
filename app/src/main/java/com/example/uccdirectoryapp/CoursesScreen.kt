package com.example.uccdirectoryapp

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.uccdirectoryapp.models.Course

@Composable
fun CoursesScreen(context: Context = LocalContext.current) {
    val db = remember { CourseHelperDatabase(context) }
    val groupedCourses = remember { mutableStateOf<Map<String, List<Course>>>(emptyMap()) }

    LaunchedEffect(Unit) {
        db.populateTestCourses()
        groupedCourses.value = db.getAllCourses().groupBy { it.department }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00205B)) // Deep Blue Background
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        groupedCourses.value.forEach { (department, courses) ->
            item {
                Text(
                    text = department,
                    color = Color.Black,
                    style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .background(Color(0xFFFFC72C)) // Optional: yellow background for header
                        .padding(8.dp)
                )
            }
            items(courses) { course ->
                CourseItem(course)
            }
        }
    }
}

@Composable
fun CourseItem(course: Course) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)) // Faint White Background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "${course.code} - ${course.name}",
                color = Color.Black,
                style = androidx.compose.material3.MaterialTheme.typography.titleMedium
            )
            Text(
                "Credits: ${course.credits}",
                color = Color.Black,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
            )
            Text(
                "Pre-requisites: ${course.preRequisites}",
                color = Color.Black,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Description: ${course.description}",
                color = Color.Black,
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall
            )
        }
    }
}

