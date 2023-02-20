package com.example.grip_task.model.roomdatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("transfers")
data class Transfer(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val date: String,
    val sender: String,
    val receiver: String,
    val amount: Int
)
