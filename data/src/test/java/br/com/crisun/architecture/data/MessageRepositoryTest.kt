package br.com.crisun.architecture.data

import br.com.crisun.architecture.data.database.dao.MessageDao
import br.com.crisun.architecture.data.network.MessageApi
import br.com.crisun.architecture.data.network.model.MessageResponse
import br.com.crisun.architecture.data.repository.MessageRepositoryImpl
import br.com.crisun.architecture.domain.model.Failure
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Success
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.util.*

class MessageRepositoryTest {
    private val api: MessageApi = mockk()
    private val dao: MessageDao = mockk()
    private val repository = MessageRepositoryImpl(api, dao)

    private val messageResponse = MessageResponse(1, "text", Date())
    private val failureResponseBody = "network error".toResponseBody("text".toMediaType())

    @Test
    fun `Test calls api upon success`() {
        val expected = Success(Message(1, "text", Date()))
        coEvery { api.getMessage() } returns Response.success(messageResponse)

        val result = runBlocking {
            repository.getMessage()
        }

        coVerify { api.getMessage() }
        assertEquals(expected, result)
    }

    @Test
    fun `Test calls api upon failure`() {
        val expected = Failure(Exception("network error"))
        coEvery { api.getMessage() } returns Response.error(666, failureResponseBody)

        val result = runBlocking {
            repository.getMessage()
        }

        coVerify { api.getMessage() }
        assertEquals(expected, result)
    }
}
