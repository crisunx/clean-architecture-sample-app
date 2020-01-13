package br.com.crisun.architecture.data.remote

import br.com.crisun.architecture.data.remote.model.MessageResponse
import retrofit2.Response
import retrofit2.http.GET

interface MessageApi {
    @GET("/message")
    suspend fun getMessage(): Response<MessageResponse>
}
