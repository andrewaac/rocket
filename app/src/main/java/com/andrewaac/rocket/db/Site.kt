package com.andrewaac.rocket.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class Site(
    var site_id: String = "",
    var site_name: String = "",
    var site_name_long: String = ""
) {
    override fun toString(): String {
        return "Site(site_id='$site_id', site_name='$site_name', site_name_long='$site_name_long')"
    }
}

class SiteConverter {
    @TypeConverter
    fun toString(site: Site): String {
        return Gson().toJson(site)
    }

    @TypeConverter
    fun toSite(string: String): Site {
        return Gson().fromJson(string, Site::class.java)
    }
}