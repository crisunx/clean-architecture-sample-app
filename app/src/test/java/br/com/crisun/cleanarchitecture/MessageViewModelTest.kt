package br.com.crisun.cleanarchitecture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.crisun.architecture.domain.model.Error
import br.com.crisun.architecture.domain.model.Failure
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.StatisticByHour
import br.com.crisun.architecture.domain.model.Success
import br.com.crisun.architecture.domain.service.MessageService
import br.com.crisun.architecture.domain.service.StatisticService
import br.com.crisun.cleanarchitecture.ui.main.MainViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MessageViewModelTest {
    val dispatcher = TestCoroutineDispatcher()

    lateinit var viewModel: MainViewModel
    lateinit var messageService: MessageService
    lateinit var statisticService: StatisticService

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        messageService = mockkClass(MessageService::class, relaxed = true)
        statisticService = mockkClass(StatisticService::class, relaxed = true)

        viewModel = MainViewModel(messageService, statisticService)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Test getMessage sets liveData value when success`() {
        dispatcher.runBlockingTest {
            val expected = Success(Message(1, "test"))

            coEvery { messageService.getMessage() } returns Success(Message(1, "test"))

            viewModel.process()

            assertEquals(null, viewModel.errorLiveData.value)
            assertEquals(expected.data, viewModel.messageLiveData.value)
        }
    }

    @Test
    fun `Test getMessage sets liveData value when failure`() {
        dispatcher.runBlockingTest {
            val expected = Failure(Error(1, "Generic error"))

            coEvery { messageService.getMessage() } returns Failure(Error(1, "Generic error"))

            viewModel.process()

            assertEquals(null, viewModel.messageLiveData.value)
            assertEquals(expected.error, viewModel.errorLiveData.value)
        }
    }

    @Test
    fun `Test getStatistics sets liveData`() {
        dispatcher.runBlockingTest {
            val expected = listOf(StatisticByHour("12", 10), StatisticByHour("13", 15))

            coEvery { statisticService.getStatistics() } returns expected.toList()
            coEvery { messageService.getMessage() } returns Success(Message(1, "test"))

            viewModel.process()

            assertEquals(expected, viewModel.statisticLiveData.value)
        }
    }
}
