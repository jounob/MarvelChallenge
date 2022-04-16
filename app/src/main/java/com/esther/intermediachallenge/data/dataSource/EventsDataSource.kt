package com.esther.intermediachallenge.data.dataSource

import com.esther.intermediachallenge.data.models.NetResult
import com.esther.intermediachallenge.data.services.EventService
import javax.inject.Inject

class EventsDataSource @Inject constructor(
    private val eventService: EventService
) : NetResult() {
    suspend fun getEvent(auth: HashMap<String, String>, limit:Int, orderBy:String,) =
        getResult { eventService.getEvents(auth, limit, orderBy) }
}