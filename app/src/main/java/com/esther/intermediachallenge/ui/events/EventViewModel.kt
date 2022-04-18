package com.esther.intermediachallenge.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esther.intermediachallenge.data.models.Comic
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

    private val _eventComicsState = MutableLiveData<EventComicsState>()
    val eventComicsState: LiveData<EventComicsState> = _eventComicsState

    init {
        events()
//        eventComics(Ev())
    }

    private fun events() {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                eventRepository.getEvents()
            }.run {
                when (status) {
                    Resource.Status.LOADING ->
                        _eventState.value = EventState(isLoading = true)
                    Resource.Status.ERROR ->
                        _eventState.value = EventState(isError = true)
                    Resource.Status.SUCCESS ->
                        _eventState.value = EventState(isSuccess = this.data ?: emptyList())
                }
            }
        }
    }

    fun eventComics(eventId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                eventRepository.getComicsEvent(eventId)
            }.run {
                _eventComicsState.value = EventComicsState(isExpanded = true)
                when (status) {
                    Resource.Status.LOADING ->
                        _eventComicsState.value = EventComicsState(isLoading = true)
                    Resource.Status.ERROR ->
                        _eventComicsState.value = EventComicsState(isError = true)
                    Resource.Status.SUCCESS ->
                        _eventComicsState.value =
                            EventComicsState(isSuccess = this.data ?: emptyList())
                }
            }
        }
    }

    data class EventState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val isSuccess: List<Events> = emptyList()
    )

    data class EventComicsState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val isSuccess: List<Comic> = emptyList(),
        val isExpanded: Boolean = false
    )

}

