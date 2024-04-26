package com.example.studymate

import io.ktor.client.request.get
import io.ktor.client.call.body

class resourceRepository {
    suspend fun getCategories(): List<resource> { // fun takes time to give response, it might not be immediate
        val response = KtorClient.httpClient
            .get("https://api.escuelajs.co/api/v1/categories") //data api file
            .body<List<resource>>()

        return response
    }
}
