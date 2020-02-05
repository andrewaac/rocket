package com.andrewaac.rocket.db.launch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrewaac.rocket.db.launch.Launch

@Dao
interface LaunchDao {

    @Query("SELECT * FROM launches")
    fun getAllLaunches(): LiveData<List<Launch>>

    @Query("SELECT * FROM launches WHERE launch_success = 1")
    fun getSuccessfulLaunches(): List<Launch>

    @Query("SELECT * FROM launches ORDER BY launch_date_utc")
    fun getOrderedLaunches(): List<Launch>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLaunch(launch: Launch)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLaunch(launches: List<Launch>)
}