package com.example.weatheapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatheapp.model.AppState
import com.example.weatheapp.model.WeatherDTO
import com.example.weatheapp.repository.DetailsRepository
import com.example.weatheapp.repository.DetailsRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepository = DetailsRepositoryImpl()
) : ViewModel() {

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getWeatherDetailsFromServer(lat, lon, callBack)
    }

    private val callBack = object : Callback<WeatherDTO> {
        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            response.body()?.let {
                detailsLiveData.postValue(AppState.Success(it))
            } ?: detailsLiveData.postValue(AppState.Error(Throwable("Неполные данные")))
        }

        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: "Ошибка запроса")))
        }
    }
}
