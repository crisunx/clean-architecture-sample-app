package br.com.crisun.architecture.domain.usecase

import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.repository.MessageRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.*

class SaveMessageUseCaseTest {
    private val repository: MessageRepository = mockk()
    private val useCase by lazy { SaveMessageUseCaseImpl(repository) }
    private val messages by lazy { listOf(Message(1, "test1", Date()), Message(2, "test2", Date())).toTypedArray() }

    @Test
    fun `Calls MessageRepository Insert one message`() {
        coEvery { repository.insert(messages.first()) } just Runs

        runBlocking { useCase(messages.first()) }

        coVerify { repository.insert(messages.first()) }
    }

    @Test
    fun `Calls MessageRepository Insert messages`() {
        coEvery { repository.insert(*messages) } just Runs

        runBlocking { useCase(*messages) }

        coVerify { repository.insert(*messages) }
    }
}
