package com.andrewaac.rocket.db.launch

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrewaac.rocket.db.Links
import com.andrewaac.rocket.db.Rocket
import com.andrewaac.rocket.db.Site

@Entity(tableName = "launches")
data class Launch(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var flight_number: String = "",
    var mission_name: String = "",
    var launch_date_utc: String = "",
    var rocket: Rocket? = null,
    var launch_site: Site? = null,
    var links: Links? = null
) {
    override fun toString(): String {
        return "Launch(flight_number='$flight_number', mission_name='$mission_name', launch_date_utc='$launch_date_utc', rocket=$rocket, launch_site=$launch_site, links=$links)"
    }
}