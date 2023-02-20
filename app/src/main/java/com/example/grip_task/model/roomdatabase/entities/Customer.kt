package com.example.grip_task.model.roomdatabase.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity("customers")
@Parcelize
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val email:String,
    val balance:Int):Parcelable
