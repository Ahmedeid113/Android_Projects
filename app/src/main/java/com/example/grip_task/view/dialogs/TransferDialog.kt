package com.example.grip_task.view.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.grip_task.R
import com.example.grip_task.model.roomdatabase.entities.Customer
import com.example.grip_task.model.roomdatabase.entities.Transfer
import com.example.grip_task.viewmodel.DatabaseViewModel
import java.text.SimpleDateFormat
import java.util.Date

class TransferDialog : DialogFragment(), OnItemSelectedListener {

    private lateinit var viewModel: DatabaseViewModel
    private val args by navArgs<TransferDialogArgs>()
    private lateinit var receiver: Customer

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireContext()).setView(R.layout.transfer_dialog_layout)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DatabaseViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        val emails = arrayListOf<String>()
        args.customers.forEach {
            emails.add(it.email)
        }

        this.dialog!!.findViewById<Button>(R.id.btn_submit).setOnClickListener {
            update()
        }
        val spinner = this.dialog!!.findViewById<Spinner>(R.id.customers_spinner)
        spinner.onItemSelectedListener = this
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            androidx.transition.R.layout.support_simple_spinner_dropdown_item,
            emails
        )
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        receiver = args.customers[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


    private fun update() {

        if (this.dialog!!.findViewById<EditText>(R.id.amount).text.isEmpty()) {
            Toast.makeText(requireContext(), "Please Enter Amount", Toast.LENGTH_SHORT).show()
            return
        }


        val amount = this.dialog!!.findViewById<EditText>(R.id.amount).text.toString().toInt()
        if (amount > args.sender.balance || args.sender.balance == 0) {
            Toast.makeText(requireContext(), "Please Check Your Balance", Toast.LENGTH_SHORT).show()
            return
        }
        //sender update
        viewModel.updateCustomer((args.sender.balance - amount), args.sender.id)
        //receiver update
        viewModel.updateCustomer((receiver.balance + amount), receiver.id)
        Toast.makeText(requireContext(), "Transfer Success", Toast.LENGTH_SHORT).show()
        addTransfer(amount)
        this.dismiss()


    }

    @SuppressLint("SimpleDateFormat")
    private fun addTransfer(amount:Int){
        viewModel.insertTransfer(Transfer(SimpleDateFormat("hmmss").format(Date()).toInt(), SimpleDateFormat("dd/M/yyyy hh:mm:a").format(Date()),args.sender.email,receiver.email,amount))
    }

}