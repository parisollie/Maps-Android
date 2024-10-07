package com.pjff.mapas.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjff.mapas.models.GoogleGeoResults
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class SearchViewModel: ViewModel() {

    //Vid 273
    var lat by mutableDoubleStateOf(0.0)
        private set
    var long by mutableDoubleStateOf(0.0)
        private set
    var address by mutableStateOf("")
        private set
    var show by mutableStateOf(false)
        private set

    //Vid 274
    fun getLocation(search: String){
        viewModelScope.launch {
            //Mi api key
            val apikey = "AIzaSyBrJ5XD8bHn7rah4s799-SUTkHL-wivZLk"

            val url =  "https://maps.googleapis.com/maps/api/geocode/json?address=$search&key=$apikey"
            val response = withContext(Dispatchers.IO){
                URL(url).readText()
            }

            val results = Gson().fromJson(response, GoogleGeoResults::class.java)

            if (results.results.isNotEmpty()){
                show = true
                lat = results.results[0].geometry.location.lat
                long = results.results[0].geometry.location.lng
                address = results.results[0].formatted_address
            }else {
                Log.d("Fail","No funciona")
            }

        }
    }

}














