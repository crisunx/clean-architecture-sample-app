package br.com.crisun.cleanarchitecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.architecture.domain.model.MessagesReport
import br.com.crisun.architecture.domain.model.onFailure
import br.com.crisun.architecture.domain.model.onSuccess
import br.com.crisun.architecture.domain.usecase.MessageReportUseCase
import br.com.crisun.architecture.domain.usecase.RequestMessageUseCase
import br.com.crisun.architecture.domain.usecase.SaveMessageUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val messageReportUseCase: MessageReportUseCase, private val requestMessageUseCase: RequestMessageUseCase, private val saveMessageUseCase: SaveMessageUseCase) : ViewModel() {
    private val error = MutableLiveData<String>()
    private val message = MutableLiveData<Message>()
    private val messagesByHour = MutableLiveData<List<MessagesReport>>()

    fun getError(): LiveData<String> = error

    fun getMessage(): LiveData<Message> = message

    fun getMessagesByHour(): LiveData<List<MessagesReport>> = messagesByHour

    fun requestMessage() {
        viewModelScope.launch {
            requestMessageUseCase().onSuccess {
                message.postValue(it)
            }.onFailure {
                error.postValue(it.toString())
            }
        }
    }

    fun saveMessage(message: Message) {
        viewModelScope.launch {
            saveMessageUseCase(message)
        }
    }

    fun messageReport() {
        viewModelScope.launch {
            messagesByHour.postValue(messageReportUseCase())
        }
    }
}
