package com.esther.intermediachallenge.data.repositories

import com.esther.intermediachallenge.data.dataSource.EventsDataSource
import com.esther.intermediachallenge.data.models.Events
import com.esther.intermediachallenge.data.models.Resource
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventsDataSource: EventsDataSource
) : BaseRepository() {
    suspend fun getEvents(orderBy: String = "startDate", limit: Int = 25): Resource<List<Events>> =
        eventsDataSource.getEvent(authParams.getMap(), orderBy, limit)
}