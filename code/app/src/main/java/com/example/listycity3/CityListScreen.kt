package com.example.listycity3

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity3.ui.theme.ListyCity3Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun CityListScreen(
    cities: List<City>,
    modifier: Modifier = Modifier,
    onAddCity: (City) ->Unit,
    onReplaceCity: (City, City) ->Unit
) {

    var citySelected by remember {mutableStateOf(City("","") )}


    var newProvince by remember {mutableStateOf(value = "")}
    var newCityName by remember {mutableStateOf(value = "")}
    var showAddCityFields by remember {mutableStateOf(false)}


    Column(modifier = Modifier){

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            FloatingActionButton(modifier = Modifier.padding(16.dp),
                onClick = {showAddCityFields = !showAddCityFields}
                ) {

                Text("+")
            }
        }

        if (showAddCityFields){
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)

            ) {

                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    label = { Text("City Name") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = newProvince,
                    onValueChange = { newProvince = it },
                    label = { Text("Province") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    modifier = Modifier.padding(vertical = 8.dp),

                    onClick = {


                        if (newCityName.isNotBlank() &&
                            newProvince.isNotBlank() &&
                            citySelected.province == "" &&
                            citySelected.name == "") {

                            onAddCity(
                                City(name = newCityName, province = newProvince)
                            )
                            newCityName= ""
                            newProvince = ""
                            showAddCityFields = false
                        } else if(newCityName.isNotBlank() &&
                            newProvince.isNotBlank()){

                            onReplaceCity(
                                citySelected , City(newCityName,newProvince)
                            )

                            citySelected = City("","")
                            newCityName= ""
                            newProvince = ""
                            showAddCityFields = false
                        }
                    }

                ) {

                    if (citySelected.name.isNotBlank() && citySelected.province.isNotBlank()){
                        Text("Edit", modifier = Modifier.padding(8.dp))
                    } else{
                        Text(text = "Add", modifier = Modifier.padding(8.dp))
                    }
                }



            }


        }// if activity


        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(cities) { index, city ->

                CityRow(city = city, clicked = { citySelected = city }  )

                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
        }

        }




}


@Composable
fun CityRow(city: City, clicked: () -> Unit ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .clickable {
                clicked()
            }
    ) {
        Text(
            text = city.name,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = city.province,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf<City>(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Calgary", "AB")
            ),
            onAddCity = {},
            onReplaceCity = {} as (City, City) -> Unit
        )
    }
}