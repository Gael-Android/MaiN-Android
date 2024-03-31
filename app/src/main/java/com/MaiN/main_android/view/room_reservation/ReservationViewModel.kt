package com.MaiN.main_android.view.room_reservation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.MaiN.main_android.shared_preference.SharedPreferencesManager
import com.MaiN.main_android.retrofit.ApiResponse
import com.MaiN.main_android.retrofit.RetrofitConnection
import com.MaiN.main_android.retrofit.reservation.ReservAPIService
import com.MaiN.main_android.retrofit.reservation.ReservationRequest
import com.MaiN.main_android.retrofit.reservation.toCellUiStateList
import com.MaiN.main_android.view.room_reservation.data.BottomSheetData
import com.MaiN.main_android.view.room_reservation.state.ReservationScreenUiState
import com.MaiN.main_android.view.room_reservation.state.fillTwoZero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.time.Instant
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

class ReservationViewModel : ViewModel() {
    private val _stateFlow: MutableStateFlow<ReservationScreenUiState> =
        MutableStateFlow(ReservationScreenUiState())
    val stateFlow: StateFlow<ReservationScreenUiState> = _stateFlow.asStateFlow()

    private val retrofitAPI = RetrofitConnection.getInstance().create(ReservAPIService::class.java)


    init {
        val currentTimeMillis = System.currentTimeMillis()
        setDateByMillis(currentTimeMillis)
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _stateFlow.update {
                it.copy(
                    seminarRoom1 = emptyList(),
                    seminarRoom2 = emptyList(),
                    facultyConferenceRoom = emptyList(),
                )
            }
            val year = _stateFlow.value.year.toString()
            val month = _stateFlow.value.month.fillTwoZero()
            val day = _stateFlow.value.day.fillTwoZero()
            showEvents("$year-$month-$day", "세미나실1")
            showEvents("$year-$month-$day", "세미나실2")
            showEvents("$year-$month-$day", "교수회의실")
        }
    }


    fun setDateByMillis(it: Long) {
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.of("UTC"))
        val year = dateTime.year
        val monthAsUsStyle =
            dateTime.month.getDisplayName(
                TextStyle.FULL,
                Locale.US
            ).take(3)
        val month = dateTime.month.value
        val day = dateTime.dayOfMonth
        val dayOfWeek = dateTime.dayOfWeek.getDisplayName(
            TextStyle.FULL,
            Locale.KOREA
        )


        _stateFlow.update {
            it.copy(
                year = year,
                month = month,
                usStyleMonth = monthAsUsStyle,
                day = day,
                dayOfWeek = dayOfWeek,
            )
        }

        _stateFlow.update {
            it.copy(
                daysInMonth = getDaysInMonth(year, dateTime.monthValue)
            )
        }

        _stateFlow.update {
            it.copy(
                monthDetails = getMonthDetails(year, dateTime.monthValue)
            )
        }

        _stateFlow.update {
            it.copy(
                selectedIndex = day - 1
            )
        }

    }

    // 해당 월의 일 수를 반환하는 함수
    private fun getDaysInMonth(year: Int, month: Int): Int {
        val yearMonth = YearMonth.of(year, month)
        return yearMonth.lengthOfMonth()
    }

    // 년과 월을 입력하면, 해당 월의 일자와 요일을 반환하는 함수
    private fun getMonthDetails(year: Int, month: Int): MutableList<Pair<String, Int>> {
        val monthDetails = mutableListOf<Pair<String, Int>>()
        val yearMonth = YearMonth.of(year, month)

        val firstDayOfMonth = yearMonth.atDay(1)
        val lastDayOfMonth = yearMonth.atEndOfMonth()

        var currentDate = firstDayOfMonth
        while (!currentDate.isAfter(lastDayOfMonth)) {
            monthDetails.add(
                Pair(
                    currentDate.dayOfWeek.getDisplayName(
                        TextStyle.FULL,
                        Locale.KOREA
                    )[0].toString(),
                    currentDate.dayOfMonth
                )
            )
            currentDate = currentDate.plusDays(1)
        }

        return monthDetails
    }

    fun updateSelectedIndex(index: Int) {
        _stateFlow.update {
            it.copy(
                selectedIndex = index
            )
        }
        _stateFlow.update {
            it.copy(
                dayOfWeek = it.monthDetails[index].first,
                day = it.monthDetails[index].second
            )
        }
    }

    private fun showEvents(date: String, location: String) {
        Log.d("KWK", "showEvents $date $location")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofitAPI.showEvents(date, location)
                if (response.isSuccessful) {
                    val cellUiStateList = response.body()?.toCellUiStateList()
                    Log.d("KWK-showEvent-SUCCESS", cellUiStateList.toString())
                    when (location) {
                        "세미나실1" -> {
                            _stateFlow.update {
                                it.copy(
                                    seminarRoom1 = cellUiStateList ?: emptyList()
                                )
                            }
                            Log.d("KWK", _stateFlow.value.seminarRoom1.toString())
                        }

                        "세미나실2" -> {
                            _stateFlow.update {
                                it.copy(
                                    seminarRoom2 = cellUiStateList ?: emptyList()
                                )
                            }
                            Log.d("KWK", _stateFlow.value.seminarRoom2.toString())
                        }

                        "교수회의실" -> {
                            _stateFlow.update {
                                it.copy(
                                    facultyConferenceRoom = cellUiStateList ?: emptyList()
                                )
                            }
                            Log.d("KWK", _stateFlow.value.facultyConferenceRoom.toString())
                        }

                        else -> {

                        }
                    }
                } else {
                    Log.d("KWK-showEvent-FAIL", "${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("KWK-showEvent-EXCEPTION", e.message.toString())
                _stateFlow.update {
                    it.copy(
                        seminarRoom1 = emptyList(),
                        seminarRoom2 = emptyList(),
                        facultyConferenceRoom = emptyList(),
                    )
                }
            }
        }
    }

    fun updateBottomSheetData(bottomSheetData: BottomSheetData) {
        _stateFlow.update {
            it.copy(
                bottomSheetData = bottomSheetData
            )
        }
    }

    fun addEvent(reservationRequest: ReservationRequest) = flow {
        runCatching {
            retrofitAPI.addEvent(reservationRequest)
        }.onSuccess {
            if (it.isSuccessful) {
                Log.d("KWK-addEvent-SUCCESS", it.body().toString())
                refresh()
                emit(ApiResponse.Success)
            } else {
                Log.d("KWK-addEvent-FAIL", "${it.errorBody()?.string()}")
                emit(
                    ApiResponse.Failure(
                        JSONObject(
                            it.errorBody()?.string()!!
                        ).getString("message")
                    )
                )
            }
        }.onFailure {
            Log.d("KWK-addEvent-Exception", it.message.toString())
            emit(ApiResponse.Error(it.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun deleteEvent(eventId: String) {
        Log.d("KWK-deleteEvent", eventId)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val schoolId = SharedPreferencesManager.getSchoolNumber("schoolNumber", "")
                val response = retrofitAPI.deleteEvent(eventId, schoolId)
                if (response.isSuccessful) {
                    Log.d("KWK-deleteEvent-SUCCESS", response.body().toString())
                    refresh()
                } else {
                    Log.d("KWK-deleteEvent-FAIL", response.errorBody()?.string()!!)
                }
            } catch (e: Exception) {
                Log.d("KWK-deleteEvent-Exception", e.message.toString())
            }
        }
    }
}

