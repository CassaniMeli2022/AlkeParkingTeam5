package com.example.alkeparking

import java.util.*
import kotlin.collections.ArrayList

data class Parking(
    var vehicles: MutableSet<Vehicle>,
    var vehiclesRetired: ArrayList<Vehicle>,
    val slots: Int,
){
    private var vehiclesRetiresAndEarnings: Pair<Int, Int> = Pair(0,0)


    fun addVehicle(
        plate: String,
        type: VehicleType,
        checkInTime: Long,
        discountCard: String? = null,
    ): Boolean {
        val vehicle = Vehicle(plate, type, checkInTime, discountCard)
        return if(!vehicles.contains(vehicle)){
            if (vehicles.size <= slots) {
                vehicles.add(vehicle)
                println("Welcome to AlkeParking! Vehicle plate #$plate")
                true
            } else {
                println("Sorry, Check In of Vehicle plate #$plate has Failed, we're running out of space!")
                false
            }
        }else {
            println("Sorry, Check In of Vehicle plate #$plate has Failed, this vehicle is already parked")
            false
        }

    }

    fun checkOutVehicle(plate: String, onSuccess: (Int, String) -> Unit, onError: () -> Unit) {

        var vehicle = Vehicle("vehicleNotExists", VehicleType.CAR, 0, "asdaasdasd")
        for (item in vehicles) {
            if (item.plate == plate) {
                vehicle = item
            }
        }

        if (vehicles.contains(vehicle)) {
            vehicles.remove(vehicle)
            vehiclesRetired.add(vehicle)
            val parkedTime = (Calendar.getInstance().timeInMillis - vehicle.checkInTime).toInt()
            val parkingSpace = ParkingSpace(vehicle, parkedTime.toLong())
            onSuccess(parkingSpace.calculateFee(parkedTime, vehicle.Type, vehicle.discountCard != null), vehicle.plate)
            val totalEarnings = vehiclesRetiresAndEarnings.first + parkingSpace.calculateFee(parkedTime, vehicle.Type, vehicle.discountCard != null)
            val carsRetired = vehiclesRetiresAndEarnings.second + 1
            vehiclesRetiresAndEarnings = Pair(totalEarnings, carsRetired)
        } else onError()

    }


    fun showEarnings(){
        println("${vehiclesRetiresAndEarnings.second} Vehicles have checked out and have earnings of $${vehiclesRetiresAndEarnings.first}")
    }

    fun listVehicles(){
        println("We have the next vehicles parked:")
        for(item in vehicles){
            println(item.plate)
        }
    }
}



