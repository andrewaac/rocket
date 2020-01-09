package com.andrewaac.rocket.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class Rocket(
    var rocket_id: String = "",
    var rocket_name: String = "",
    var rocket_type: String = ""
) {
    override fun toString(): String {
        return "Rocket(rocket_id='$rocket_id', rocket_name='$rocket_name', rocket_type='$rocket_type')"
    }
}

class RocketConverter {

    @TypeConverter
    fun toString(rocket: Rocket): String {
        return Gson().toJson(rocket)
    }

    @TypeConverter
    fun toRocket(string: String): Rocket {
        return Gson().fromJson(string, Rocket::class.java)
    }
}