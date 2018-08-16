package com.huma.roomforasset

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Customers::class, Employees::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun chinookDao(): ChinookDao

}