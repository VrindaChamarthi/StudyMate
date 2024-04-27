package com.example.studymate

import io.ktor.client.request.get
import io.ktor.client.call.body

class resourceRepository {
    suspend fun getResources(subjectTitle: String): List<Resource> { // fun takes time to give response, it might not be immediate
        val response = KtorClient.httpClient
            .get("https://crudcrud.com/api/99ca3df57f9d4c08a219b925e9fd402e/$subjectTitle")
            .body<List<Resource>>()

        return response
    }
}
