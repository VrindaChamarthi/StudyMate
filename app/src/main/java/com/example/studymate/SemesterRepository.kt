package com.example.studymate

class SemesterRepository {
    suspend fun getSemesters(): List<Semesters>{
        return listOf(
            Semesters("1-1"),
            Semesters("1-2"),
            Semesters("2-1"),
            Semesters("2-2"),
            Semesters("3-1"),
            Semesters("3-2"),
            Semesters("4-1"),
            Semesters("4-2")
        )
    }
}