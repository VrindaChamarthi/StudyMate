package com.example.studymate

import kotlinx.serialization.Serializable

@Serializable
data class resource (
    val name: String,
    val pdfLink: String,
    val image: String
)