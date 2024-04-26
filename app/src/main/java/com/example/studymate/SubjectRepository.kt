package com.example.studymate

import com.example.studymate.KtorClient
import com.example.studymate.resource
import io.ktor.client.request.get
import io.ktor.client.call.body

class subjectRepository {
    suspend fun getCategories(): List<resource> { // fun takes time to give response, it might not be immediate
        val response = KtorClient.httpClient
            .get("https://crudcrud.com/api/d610ae151985427e88eb48a7d51dc1eb/$subjectname") //data api file
            .body<List<resource>>()

        return response
    }
}
