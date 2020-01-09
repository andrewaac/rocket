package com.andrewaac.rocket.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class Links(
    var mission_patch: String = "",
    var wikipedia: String = "",
    var video_link: String = "",
    var flickr_images: ArrayList<String>? = null
) {
    override fun toString(): String {
        return "Links(mission_patch='$mission_patch', wikipedia='$wikipedia', video_link='$video_link', flickr_images=$flickr_images)"
    }
}

class LinksConverter {

    @TypeConverter
    fun toString(link: Links): String {
        return Gson().toJson(link)
    }

    @TypeConverter
    fun toLink(string: String): Links {
        return Gson().fromJson(string, Links::class.java)
    }

}