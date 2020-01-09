package com.andrewaac.rocket

import android.app.Application
import androidx.room.Room
import com.andrewaac.rocket.db.AppDatabase

class RocketApplication : Application() {

    companion object {
        var db: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "rocket-db"
        )
            .build()
    }

}