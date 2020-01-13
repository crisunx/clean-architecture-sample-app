package br.com.crisun.architecture.domain.service

import br.com.crisun.architecture.domain.model.Statistic
import br.com.crisun.architecture.domain.model.StatisticByHour
import br.com.crisun.architecture.domain.repository.StatisticRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class StatisticServiceTest {
    private val repository: StatisticRepository = mockk()
    private val service by lazy { StatisticServiceImpl(repository) }
    private val statistics by lazy { listOf(Statistic(null, Date()), Statistic(null, Date())).toTypedArray() }

    @Test
    fun `Calls StatisticRepository Insert`() {
        coEvery { repository.insert(*statistics) } just Runs

        runBlocking { service.insert(*statistics) }

        coVerify { repository.insert(*statistics) }
    }

    @Test
    fun `Calls StatisticRepository getStatistics`() {
        val expected = listOf(StatisticByHour("21", 10), StatisticByHour("22", 45))
        coEvery { repository.getStatistics() } returns expected.toList()

        val result = runBlocking { service.getStatistics() }

        assertEquals(expected, result)
        coVerify { repository.getStatistics() }
    }
}
