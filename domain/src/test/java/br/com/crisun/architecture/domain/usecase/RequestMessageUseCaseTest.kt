package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.Success
import br.com.crisun.architecture.domain.repository.MessageRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class RequestMessageUseCaseTest {
    private val repository: MessageRepository = mockk()
    private val useCase by lazy { RequestMessageUseCaseImpl(repository) }
    private val message by lazy { Message(1, "test1", Date()) }

    @Test
    fun `Calls MessageRepository getMessage`() {
        val expected = Success(message)

        coEvery { repository.getMessage() } returns Success(message)

        val result = runBlocking {
            useCase()
        }

        coVerify { repository.getMessage() }

        assertEquals(expected, result)
    }
}
