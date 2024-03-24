package com.MaiN.main_android.retrofit.reservation

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReservAPIService {
    @GET("calendar/show_event")
    suspend fun showEvents(
        @Query("date") date: String,
        @Query("location") location: String
    ): Response<ReservDataclass>

    @POST("calendar/add")
    suspend fun addEvent(
        @Body reservationRequest: ReservationRequest
    ): Response<Unit>

    @HTTP(method = "DELETE", path = "/calendar/delete/{eventid}")
    suspend fun deleteEvent(
        @Path("eventid") eventId: String,
        @Path("student_id") studentId: String,
    ): Response<Unit>
}

