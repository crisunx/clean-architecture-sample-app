package br.com.crisun.architecture.data.network

import br.com.crisun.architecture.data.network.model.MessageResponse
import retrofit2.Response
import retrofit2.http.GET

interface MessageApi {
    @GET("/message")
    suspend fun getMessage(): Response<MessageResponse>
}
