package com.andrewaac.rocket.launches

import com.andrewaac.rocket.Links
import com.andrewaac.rocket.Rocket
import com.andrewaac.rocket.Site
import com.fasterxml.jackson.annotation.JsonProperty
import org.json.JSONObject

class Launch(

//    @JsonProperty("flight_number")
    var flight_number: String = "",

//    @JsonProperty("mission_name")
    var mission_name: String = "",

//    @JsonProperty("launch_date_utc")
    var launch_date_utc: String = "",

//    @JsonProperty("rocket")
    var rocket: Rocket? = null,

//    @JsonProperty("launch_site")
    var launch_site: Site? = null,

//    @JsonProperty("links")
    var links: Links? = null
) {
    override fun toString(): String {
        return "Launch(flight_number='$flight_number', mission_name='$mission_name', launch_date_utc='$launch_date_utc', rocket=$rocket, launch_site=$launch_site, links=$links)"
    }
}