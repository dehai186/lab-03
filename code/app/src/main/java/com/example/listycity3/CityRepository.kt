package com.example.listycity3
import androidx.compose.runtime.mutableStateListOf
class CityRepository {
    private val _cities = mutableStateListOf<City>(
        City("Edmonton", "AB"),
        City("Vancouver", "BC"),
        City("Toronto", "ON")
    )

    val cities: List<City>
        get() = _cities



    fun replace(city: City, newCity:City){
        val index: Int = _cities.indexOf(city)
        _cities[index] = newCity

    }

    fun add(city:City){
        _cities.add(city)
    }
}