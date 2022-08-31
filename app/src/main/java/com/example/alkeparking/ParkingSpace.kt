package com.example.alkeparking

import java.util.*

data class ParkingSpace(
    var vehicle: Vehicle,
    var parkedTime: Long,
){
    fun calculateFee () : Int{
        var profit : Int = 0
        when(parkedTime as IntRange){
            1..2 -> vehicle.Type.fee
            
        }
        if (vehicle.discountCard != null) {
            profit = (((parkedTime - vehicle.checkInTime) * vehicle.Type.fee) * 0.85).toInt()
        } else profit = ((parkedTime - vehicle.checkInTime) * vehicle.Type.fee).toInt()

        return profit
    }
}


