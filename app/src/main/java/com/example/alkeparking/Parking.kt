package com.example.alkeparking

import java.util.*
import kotlin.collections.ArrayList

data class Parking(
    val vehicles: MutableSet<Vehicle>,
    val vehiclesRetired: ArrayList<Vehicle>,
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
            println("Sorry, Check In of Vehicle plate #$plate has Failed, this car is already parked")
            false
        }

    }

    fun checkOutVehicle(vehicle: Vehicle, plate: String, onSuccess: (Int, String) -> Unit, onError: () -> Unit) {

        var flag: Boolean = false
        for (item in vehicles) {
            if (item.plate == plate) {
                val parkingSpace = ParkingSpace(item, Calendar.getInstance().timeInMillis)
                onSuccess(parkingSpace.calculateFee(), plate)
                val totalEarnings = vehiclesRetiresAndEarnings.first + parkingSpace.calculateFee()
                val carsRetired = vehiclesRetiresAndEarnings.second + 1
                vehiclesRetiresAndEarnings = Pair(totalEarnings, carsRetired)
                vehiclesRetired.add(item)
                vehicles.remove(item)

                break
            }
        }
        if (!flag) onError()
    }

        if (vehicles.contains(vehicle)) {
            vehicles.remove(vehicle)
            vehiclesRetired.add(vehicle)
            val parkingSpace = ParkingSpace(vehicle, Calendar.getInstance().timeInMillis)
            onSuccess(parkingSpace.calculateFee(), vehicle.plate)
            val totalEarnings = vehiclesRetiresAndEarnings.first + parkingSpace.calculateFee()
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



