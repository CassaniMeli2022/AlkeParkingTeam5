package com.example.alkeparking


data class ParkingSpace(
    var vehicle: Vehicle,
    var parkedTime: Long,
){
    fun calculateFee() : Int{
        var baseProfit = 0
        var profit = 0
        baseProfit = if(parkedTime <= 2){
            parkedTime.toInt() * vehicle.Type.fee
        }else (2 * vehicle.Type.fee) + ((parkedTime.toInt() - 2) * 20)
        profit = if(vehicle.discountCard != null){
            (baseProfit * 0.85).toInt()
        }else baseProfit
        return profit
    }
}
