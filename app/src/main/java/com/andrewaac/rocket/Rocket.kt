package com.andrewaac.rocket

import com.fasterxml.jackson.annotation.JsonProperty

class Rocket(

//    @JsonProperty("rocket_name")
    var rocket_name: String = "",

//    @JsonProperty("rocket_type")
    var rocket_type: String = ""
) {
    override fun toString(): String {
        return "Rocket(rocket_name='$rocket_name', rocket_type='$rocket_type')"
    }
}