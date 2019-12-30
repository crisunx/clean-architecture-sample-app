package br.com.crisun.architecture.data.api

import br.com.crisun.architecture.domain.Message
import retrofit2.http.GET

interface MessageApi {
    @GET("/message")
    suspend fun getMessage(): Message
}
