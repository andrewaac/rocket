package com.andrewaac.rocket

import com.fasterxml.jackson.annotation.JsonProperty

class Site(

//    @JsonProperty("launch_site")
    var launch_site: String = "",

//    @JsonProperty("site_name_long")
    var site_name_long: String = ""
){
    override fun toString(): String {
        return "Site(launch_site='$launch_site', site_name_long='$site_name_long')"
    }
}