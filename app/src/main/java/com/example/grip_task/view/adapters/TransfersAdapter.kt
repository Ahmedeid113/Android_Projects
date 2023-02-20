package com.example.grip_task.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grip_task.R
import com.example.grip_task.model.roomdatabase.entities.Transfer

class TransfersAdapter(private val transfers: Array<Transfer>):RecyclerView.Adapter<TransfersAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.transfer_design,parent,false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val transfer = transfers[position]
        holder.sender.text = "Sender : ${transfer.sender}"
        holder.receiver.text = "Receiver : ${transfer.receiver}"
        holder.amount.text = "Amount : ${transfer.amount}"
        holder.date.text = "Date : ${transfer.date}"

    }

    override fun getItemCount(): Int = transfers.size

    class MyViewHolder(
        item: View,
        ) :
        RecyclerView.ViewHolder(item) {
        val sender: TextView
        val receiver: TextView
        val amount: TextView
        val date: TextView
        init {
            sender = item.findViewById(R.id.sender)
            receiver = item.findViewById(R.id.receiver)
            amount=item.findViewById(R.id.transfer_amount)
            date = item.findViewById(R.id.date)
        }
    }
}


