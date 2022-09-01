package com.example.alkeparking


data class ParkingSpace(
    var vehicle: Vehicle,
    var parkedTime: Long,
){
    fun calculateFee(parkedTime: Int, type: VehicleType, hasDiscountCard: Boolean) : Int{
        var baseProfit = 0
        var profit = 0
        baseProfit = if(parkedTime <= 2){
            parkedTime * type.fee
        }else 2 * type.fee + (parkedTime - 2) * 20
        profit = if(hasDiscountCard){
            (baseProfit * 0.85).toInt()
        }else baseProfit
        return profit
    }
}
