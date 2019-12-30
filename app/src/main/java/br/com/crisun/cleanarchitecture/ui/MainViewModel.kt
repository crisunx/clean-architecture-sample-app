package br.com.crisun.cleanarchitecture.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crisun.architecture.data.repository.MessageRepository
import br.com.crisun.architecture.domain.Message
import br.com.crisun.architecture.domain.MessagesByHour
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MessageRepository) : ViewModel() {
    val messageLiveData = MutableLiveData<Message>()
    val messagesByHourLiveData = MutableLiveData<List<MessagesByHour>>()

    fun process() {
        viewModelScope.launch {
            messageLiveData.value = withContext(Dispatchers.IO) {
                repository.getMessage().also {
                    repository.insert(it)
                }
            }

            messagesByHourLiveData.value = withContext(Dispatchers.IO) {
                repository.getMessagesByHour()
            }
        }
    }
}
