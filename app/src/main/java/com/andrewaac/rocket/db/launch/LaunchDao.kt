package com.andrewaac.rocket.db.launch

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andrewaac.rocket.db.launch.Launch

@Dao
interface LaunchDao {

    @Query("SELECT * FROM launches")
    fun getAllLaunches(): LiveData<List<Launch>>

    @Insert
    fun addLaunch(launch: Launch)

    @Insert
    fun addLaunch(launches: List<Launch>)
}