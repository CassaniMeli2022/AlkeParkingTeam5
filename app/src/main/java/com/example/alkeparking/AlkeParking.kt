package com.example.alkeparking

import java.util.*

fun main() {

    val parking = Parking(mutableSetOf())

    checkInVehicle("AA111AA", VehicleType.CAR, Calendar.getInstance().timeInMillis, parking, "card001")
    checkInVehicle("AA111AA", VehicleType.CAR, Calendar.getInstance().timeInMillis, parking, "card002")
    checkInVehicle("AA111BB", VehicleType.MOTO, Calendar.getInstance().timeInMillis, parking)
    checkInVehicle("AA111DD", VehicleType.BUS, Calendar.getInstance().timeInMillis, parking, "card003")
    checkInVehicle("AAZZZ234A", VehicleType.MINIBUS, Calendar.getInstance().timeInMillis, parking)


}

fun checkInVehicle(
    plate: String,
    type: VehicleType,
    checkInTime: Long,
    parking: Parking,
    discountCard: String? = null,
) {
    val vehicle = Vehicle(plate, type, checkInTime, discountCard)
    if (!parking.vehicles.contains(vehicle)) {
        parking.vehicles.add(vehicle)
        println("vehiculo patente: $plate estacionado")
    } else println("el vehiculo patente $plate ya se encuentra estacionado")
}

fun checkOutVehicle(
    vehicle: Vehicle,
    parking: Parking
) {
    if (parking.vehicles.contains(vehicle)) {
        parking.vehicles.remove(vehicle)
        val parkingSpace = ParkingSpace(vehicle, Calendar.getInstance().timeInMillis)
        println("el vehiculo patente: ${vehicle.plate} fue retirado y se cobro ${parkingSpace.calculateFee()}")
    } else println("el vehiculo no estuvo en el estacionamiento")
}



