package com.andrewaac.rocket.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andrewaac.rocket.db.launch.Launch
import com.andrewaac.rocket.db.launch.LaunchDao

@Database(entities = arrayOf(Launch::class), version = 1)
@TypeConverters(RocketConverter::class, SiteConverter::class, LinksConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchDao(): LaunchDao
}