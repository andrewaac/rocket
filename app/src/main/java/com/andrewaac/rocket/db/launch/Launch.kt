package com.andrewaac.rocket.db.launch

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.PrimaryKey
import com.andrewaac.rocket.db.Links
import com.andrewaac.rocket.db.Rocket
import com.andrewaac.rocket.db.Site

@Entity(tableName = "launches")
data class Launch(
    @PrimaryKey(autoGenerate = false)
    var flight_number: String,
    var launch_success: Boolean,
    var mission_name: String?,
    var details: String?,
    var launch_date_utc: String?,
    var rocket: Rocket?,
    var launch_site: Site?,
    var links: Links?
) {
    override fun toString(): String {
        return "Launch(flight_number='$flight_number', mission_name='$mission_name', details='$details', launch_date_utc='$launch_date_utc', rocket=$rocket, launch_site=$launch_site, links=$links)"
    }
}