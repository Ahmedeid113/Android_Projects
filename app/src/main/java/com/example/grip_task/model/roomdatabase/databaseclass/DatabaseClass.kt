package com.example.grip_task.model.roomdatabase.databaseclass

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grip_task.model.roomdatabase.dao.DatabaseAccessObject
import com.example.grip_task.model.roomdatabase.entities.Customer
import com.example.grip_task.model.roomdatabase.entities.Transfer

@Database(entities = [Customer::class, Transfer::class], version = 1)
abstract class DatabaseClass : RoomDatabase() {

    abstract fun myDao(): DatabaseAccessObject

    companion object {
        private var databaseInstance: DatabaseClass? = null
        fun getInstance(context: Context): DatabaseClass {
            if (databaseInstance == null) {
                synchronized(this) {
                    val instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseClass::class.java,
                            "GRIP_task"
                        ).createFromAsset("database/GRIP_task.db").build()
                    databaseInstance = instance
                }
            }
            return databaseInstance!!
        }
    }
}