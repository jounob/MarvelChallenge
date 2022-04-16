package com.esther.intermediachallenge.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esther.intermediachallenge.data.models.Events
import com.esther.intermediachallenge.data.models.Resource
import com.esther.intermediachallenge.data.repositories.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventRepository: EventRepository
) : ViewModel() {
    private val _eventState = MutableLiveData<EventState>()
    val eventState: LiveData<EventState> = _eventState

    init {
        eventComics()
    }

    private fun eventComics() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                eventRepository.getEvents()
            }.run {
                when (status) {
                    Resource.Status.LOADING ->
                        _eventState.value = EventState(isLoading = true)
                    Resource.Status.ERROR ->
                        _eventState.value = EventState(isError = true)
                    Resource.Status.SUCCESS -> EventState(isSuccess = this.data ?: emptyList())
                }
            }
        }
    }

}

data class EventState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: List<Events> = emptyList()
)