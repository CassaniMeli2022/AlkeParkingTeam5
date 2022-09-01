package com.example.alkeparking

import java.util.*

fun main() {

    val parking = Parking(mutableSetOf(), arrayListOf(), 20)

    println("\n ADDING VEHICLES ------------------------- \n")

    // add 20 vehicles
    for(i in 0..parking.slots){
        var card:String? = null
        if(i.mod(2) == 0){
            card = "card0${i}"
        }
        parking.addVehicle("AA1${i}AA", VehicleType.values().toList().shuffled().first(), Calendar.getInstance().timeInMillis, card)
    }

    println("\nADDING VEHICLES THAT ARE ALREADY PARKED  -------------------------\n")

    // add a vehicle that is already parked
    parking.addVehicle("AA120AA", VehicleType.CAR, Calendar.getInstance().timeInMillis, "card021")

    println("\nADDING VEHICLES WHEN PARKING HAS NO SPACE -------------------------\n")

    // add a vehicle when parking has no space
    parking.addVehicle("AA121AA", VehicleType.CAR, Calendar.getInstance().timeInMillis, "card021")

    println("\nREMOVING VEHICLES -------------------------\n")

    val funOnSuccess = fun(bill: Int, plate: String){
        println("Vehicle plate #${plate} retired. Your Fee is: $${bill}")
    }

    val funOnError = {println("Sorry, the Check Out Failed")}

    //removing vehicles
    parking.checkOutVehicle("AA110AA", funOnSuccess, funOnError)
    parking.checkOutVehicle("AA111AA", funOnSuccess, funOnError)

    //trying to remove a Vehicle which already has been removed

    println("\nREMOVING VEHICLES WHICH ALREADY HAS BEEN REMOVED -------------------------\n")


    parking.checkOutVehicle("AA110AA", funOnSuccess, funOnError)

    println("\nSHOW EARNINGS -------------------------\n")

    //show Earnings
    parking.showEarnings()

    println("\nLIST VEHICLES PARKED-------------------------\n")

    //list vehicles
    parking.listVehicles()
}

