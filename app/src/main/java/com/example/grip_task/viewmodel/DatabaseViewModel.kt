package com.example.grip_task.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.grip_task.model.roomdatabase.dao.DatabaseAccessObject
import com.example.grip_task.model.roomdatabase.databaseclass.DatabaseClass
import com.example.grip_task.model.roomdatabase.entities.Customer
import com.example.grip_task.model.roomdatabase.entities.Transfer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

     val customersData: MutableStateFlow<Array<Customer>> = MutableStateFlow(arrayOf())
     val transfersData: MutableStateFlow<Array<Transfer>> = MutableStateFlow(arrayOf())
    private val accessObject: DatabaseAccessObject

    init {
        accessObject = DatabaseClass.getInstance(application).myDao()
    }

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            accessObject.insertCustomer(customer)
        }
    }

    fun updateCustomer(newBalance:Int,id:Int){
        viewModelScope.launch {
            accessObject.updateCustomer(newBalance,id)
        }
    }

    fun insertTransfer(transfer: Transfer) {
        viewModelScope.launch(Dispatchers.IO) {
            accessObject.insertTransfer(transfer)
        }
    }

    fun getAllCustomers(): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            customersData.emit(accessObject.getCustomers())
        }
    }

    fun getAllTransfers() :Job{
        return viewModelScope.launch(Dispatchers.IO) {
            transfersData.emit(accessObject.getTransfers())
        }
    }


}