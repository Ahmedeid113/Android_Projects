package com.example.grip_task.model.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grip_task.model.roomdatabase.entities.Customer
import com.example.grip_task.model.roomdatabase.entities.Transfer


@Dao
interface DatabaseAccessObject {
    @Insert(Customer::class)
    suspend fun insertCustomer(customer: Customer)
    @Insert(Transfer::class)
    suspend fun insertTransfer(transfer: Transfer)
    @Query("UPDATE customers SET balance=:newBalance WHERE id=:id")
    suspend fun updateCustomer(newBalance:Int,id:Int)

    @Query(value = "SELECT * FROM customers")
    suspend fun getCustomers():Array<Customer>
    @Query(value = "SELECT * FROM transfers")
    suspend fun getTransfers():Array<Transfer>
}
