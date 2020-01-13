package br.com.crisun.cleanarchitecture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.crisun.architecture.domain.service.MessageService
import br.com.crisun.cleanarchitecture.ui.main.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MessageViewModelTest {
    val dispatcher = TestCoroutineDispatcher()

    lateinit var service: MessageService
    lateinit var viewModel: MainViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        service = mockkClass(MessageService::class)
        viewModel = MainViewModel(service)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun addition_isCorrect() {
        dispatcher.runBlockingTest {
            coEvery { service.getMessage() } returns mockk()
            coEvery { service.getMessagesByHour() } returns mockk()

            viewModel.process()

            assertEquals(null, viewModel.messageLiveData.value)
        }
    }
}
