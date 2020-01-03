package br.com.crisun.architecture.domain.repository

import br.com.crisun.architecture.domain.Message
import br.com.crisun.architecture.domain.model.Result

interface WeatherRepository {
  suspend fun getMessage(location: String): Result<Message>
}