package br.com.crisun.cleanarchitecture.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crisun.architecture.domain.service.MessageService
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.MessagesByHour
import br.com.crisun.architecture.domain.model.onFailure
import br.com.crisun.architecture.domain.model.onSuccess
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger

class MainViewModel(private val service: MessageService) : ViewModel(), AnkoLogger {
    val errorLiveData = MutableLiveData<String>()
    val messageLiveData = MutableLiveData<Message>()
    val messagesByHourLiveData = MutableLiveData<List<MessagesByHour>>()

    fun process() {
        viewModelScope.launch {

            service.getMessage().onSuccess {
                errorLiveData.value = ""
                messageLiveData.value = it

                service.insert(it)
            }.onFailure {
                messageLiveData.value = null
                errorLiveData.value = it.toString()
            }

            messagesByHourLiveData.value = service.getMessagesByHour()
        }
    }
}
