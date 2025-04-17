package com.example.uccdirectoryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uccdirectoryapp.ui.theme.UCCDirectoryAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UCCDirectoryAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController) }
        composable("courses") { CoursesScreen() }
        composable("faculty_directory") { FacultyDirectoryScreen() }
        composable("admissions") { AdmissionsScreen() }
        composable("social_media") { SocialMediaScreen()}



    }
}
