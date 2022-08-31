package com.example.alkeparking

import java.util.*

data class Vehicle(
    val plate: String,
    val Type: VehicleType,
    var checkInTime: Long = Calendar.getInstance().timeInMillis,
    var discountCard: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (other is Vehicle) {
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int = this.plate.hashCode()
}

//