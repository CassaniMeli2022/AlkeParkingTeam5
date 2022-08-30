package com.example.alkeparking

import java.util.*

fun main() {

    val parking = Parking(mutableSetOf())

    checkInVehicle("AA111AA", VehicleType.CAR, Calendar.getInstance().timeInMillis, parking, true)
    checkInVehicle("AA111AA", VehicleType.CAR, Calendar.getInstance().timeInMillis, parking, true)
    checkInVehicle("AA111BB", VehicleType.MOTO, Calendar.getInstance().timeInMillis, parking)
    checkInVehicle("AA111DD", VehicleType.BUS, Calendar.getInstance().timeInMillis, parking, true)
    checkInVehicle("AAZZZ234A", VehicleType.MINIBUS, Calendar.getInstance().timeInMillis, parking)

}

fun checkInVehicle(
    plate: String,
    type: VehicleType,
    checkInTime: Long,
    parking: Parking,
    discountCard: Boolean? = null,
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
        var profit: Long
        if (vehicle.discountCard == true) {
            profit =
                (((Calendar.getInstance().timeInMillis - vehicle.checkInTime) * vehicle.Type.fee) * 0.85).toLong()
        } else profit =
            (Calendar.getInstance().timeInMillis - vehicle.checkInTime) * vehicle.Type.fee
        println("el vehiculo patente: ${vehicle.plate} fue retirado y se cobro $profit")

    } else println("el vehiculo no estuvo en el estacionamiento")
}

