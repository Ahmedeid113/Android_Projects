package com.example.grip_task.view.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavHost
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.grip_task.databinding.FragmentUserDetailsBinding
import com.example.grip_task.view.dialogs.TransferDialog


class UserDetailsFragment : Fragment(), View.OnClickListener {
    private val args by navArgs<UserDetailsFragmentArgs>()


    private lateinit var binding: FragmentUserDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.customerName.text = Editable.Factory().newEditable("name : ${args.customer.name}")
        binding.customerEmail.text =
            Editable.Factory().newEditable("email : ${args.customer.email}")
        binding.customerBalance.text =
            Editable.Factory().newEditable("balance : ${args.customer.balance}")
    }

    override fun onResume() {
        super.onResume()
        binding.btnTransfer.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.btnTransfer.id -> {
                val action=UserDetailsFragmentDirections.actionUserDetailsFragmentToTransferDialog(args.customers,args.customer)
                findNavController().navigate(action)
            }
        }
    }
}