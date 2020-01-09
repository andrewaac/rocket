package com.andrewaac.rocket

class Links(

//    @JsonProperty("mission_patch")
    var mission_patch: String = "",

//    @JsonProperty("wikipedia")
    var wikipedia: String = "",

//    @JsonProperty("video_link")
    var video_link: String = "",

//    @JsonProperty("flickr_images")
    var flickr_images: ArrayList<String>? = null
) {
    override fun toString(): String {
        return "Links(mission_patch='$mission_patch', wikipedia='$wikipedia', video_link='$video_link', flickr_images=$flickr_images)"
    }
}