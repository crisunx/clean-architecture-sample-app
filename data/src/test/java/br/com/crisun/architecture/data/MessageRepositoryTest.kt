package br.com.crisun.architecture.data

import br.com.crisun.architecture.data.database.dao.MessageDao
import br.com.crisun.architecture.data.network.MessageApi
import br.com.crisun.architecture.data.network.model.MessageResponse
import br.com.crisun.architecture.data.repository.MessageRepositoryImpl
import br.com.crisun.architecture.domain.model.Error
import br.com.crisun.architecture.domain.model.Failure
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Success
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class MessageRepositoryTest {
    private val api: MessageApi = mockk()
    private val dao: MessageDao = mockk()
    private val repository = MessageRepositoryImpl(api, dao)

    private val messageResponse = MessageResponse(1, "text")
    private val failureResponseBody = ResponseBody.create(MediaType.parse("text"), "network error")

    @Test
    fun `Test calls api upon success`() {
        val expected = Success(Message(1, "text"))
        coEvery { api.getMessage() } returns Response.success(messageResponse)

        val result = runBlocking {
            repository.getMessage()
        }

        coVerify { api.getMessage() }
        assertEquals(expected, result)
    }

    @Test
    fun `Test calls api upon failure`() {
        val expected = Failure(Error(666, "network error"))
        coEvery { api.getMessage() } returns Response.error(666, failureResponseBody)

        val result = runBlocking {
            repository.getMessage()
        }

        coVerify { api.getMessage() }
        assertEquals(expected, result)
    }
}
