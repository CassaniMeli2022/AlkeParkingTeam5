package com.example.alkeparking


data class ParkingSpace(
    var vehicle: Vehicle,
    var parkedTime: Long,
){
    fun calculateFee() : Int{
        var baseProfit = 0
        var profit = 0
        val hours = millisToHour(parkedTime)
        baseProfit = if(parkedTime <= 2){
            hours * vehicle.Type.fee
        }else 2 * vehicle.Type.fee + (hours - 2) * 20
        profit = if(vehicle.discountCard != null){
            (baseProfit * 0.85).toInt()
        }else baseProfit
        return profit
    }

    private fun millisToHour(parkedTime: Long): Int{
        return (parkedTime / 100000000000).toInt()
    }
}
