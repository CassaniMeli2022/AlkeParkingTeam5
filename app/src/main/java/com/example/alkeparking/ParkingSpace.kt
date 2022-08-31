package com.example.alkeparking


data class ParkingSpace(
    var vehicle: Vehicle,
    var parkedTime: Long,
){
    fun calculateFee() : Int{
        var baseProfit: Int = 0
        var profit: Int = 0
        baseProfit = if(parkedTime <= 2){
            parkedTime.toInt() * vehicle.Type.fee
        }else (2 * vehicle.Type.fee) + (((parkedTime.toInt() - 2) * 20) + vehicle.Type.fee * 2)
        profit = if(vehicle.discountCard != null){
            (baseProfit * 0.85).toInt()
        }else baseProfit
        return profit
    }
}
