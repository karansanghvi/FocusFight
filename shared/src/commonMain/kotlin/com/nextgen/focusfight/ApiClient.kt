package com.nextgen.focusfight

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class SignupRequest(
    val email: String,
    val phone: String,
    val password: String
)

@Serializable
data class SignupResponse(
    val message: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val message: String,
    val user: User?=null
)

@Serializable
data class User(
    val id: Int,
    val email: String,
    val phone: String,
    val created_at: String
)

class ApiClient {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun signup(email: String, phone: String, password: String): SignupResponse {
        val response = httpClient.post("http://192.168.0.235:5000/api/users/register") {
            contentType(ContentType.Application.Json)
            setBody(SignupRequest(email, phone, password))
        }
        return response.body()
    }

    suspend fun login(email: String, password: String): LoginResponse {
        val response = httpClient.post("http://192.168.0.235:5000/api/users/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(email, password))
        }
        return response.body()
    }
}
