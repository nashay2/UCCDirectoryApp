package com.example.uccdirectoryapp.models

data class Course(
    val code: String,
    val name: String,
    val credits: Int,
    val preRequisites: String,
    val description: String,
    val department: String,
    val id: Int? = null
)