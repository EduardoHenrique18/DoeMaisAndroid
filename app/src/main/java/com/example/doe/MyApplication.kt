package com.example.doe

import android.app.Application
import androidx.room.Room
import com.example.doe.data.local.local.AppDatabase

class MyApplication : Application() {
    companion object {
        var recipeDb: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        recipeDb = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "AppDatabase"
        ).build()
    }
}