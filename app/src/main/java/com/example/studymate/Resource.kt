package com.example.studymate

import kotlinx.serialization.Serializable

@Serializable
data class Resource (
    val name: String = "",
    val pdfLink: String = "",
    val image: String = ""
) : java.io.Serializable