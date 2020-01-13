package br.com.crisun.cleanarchitecture.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crisun.architecture.domain.model.*
import br.com.crisun.architecture.domain.service.MessageService
import br.com.crisun.architecture.domain.service.StatisticService
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val messageService: MessageService, private val statisticService: StatisticService) : ViewModel() {
    val errorLiveData = MutableLiveData<Error>()
    val messageLiveData = MutableLiveData<Message>()
    val statisticLiveData = MutableLiveData<List<StatisticByHour>>()

    fun process() {
        viewModelScope.launch {

            messageService.getMessage().onSuccess {
                errorLiveData.value = null
                messageLiveData.value = it

                statisticService.insert(Statistic(null, Date()))
            }.onFailure {
                messageLiveData.value = null
                errorLiveData.value = it
            }

            statisticLiveData.value = statisticService.getStatistics()
        }
    }
}
