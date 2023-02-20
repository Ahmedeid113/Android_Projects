package com.example.grip_task.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.grip_task.R
import com.example.grip_task.model.roomdatabase.entities.Customer
import com.example.grip_task.view.fragments.AllUsersFragmentDirections

class AllCustomersFragmentAdapter(
    private val customers: Array<Customer>,
) :
    RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.customer_design, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val customer = customers[position]
        holder.name.text = "Name : ${customer.name}"
        holder.email.text = "Email : ${customer.email}"
        holder.balance.text = "Balance : ${customer.balance}"

        holder.itemView.setOnClickListener {

            val action=AllUsersFragmentDirections.actionUsersFragmentToUserDetailsFragment(customer,customers)
            holder.itemView.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int = customers.size


}

class MyViewHolder(
    item: View,

) :
    RecyclerView.ViewHolder(item) {
    val name: TextView
    val email: TextView
    val balance: TextView

    init {
        name = item.findViewById(R.id.customer_name)
        email = item.findViewById(R.id.customer_email)
        balance = item.findViewById(R.id.customer_balance)
    }

}

/*
interface CustomersRecyclerClickListener {
    fun onCustomerClicked(customer: Customer)
}*/
