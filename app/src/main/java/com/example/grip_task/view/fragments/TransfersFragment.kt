package com.example.grip_task.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.grip_task.databinding.FragmentTransfersBinding
import com.example.grip_task.view.adapters.TransfersAdapter
import com.example.grip_task.viewmodel.DatabaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransfersFragment : Fragment() {

    private lateinit var binding: FragmentTransfersBinding
    private lateinit var viewModel: DatabaseViewModel
    private lateinit var adapter: TransfersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransfersBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllTransfers().invokeOnCompletion {
            adapter = TransfersAdapter(viewModel.transfersData.value)
            lifecycleScope.launch(Dispatchers.Main){
                if(viewModel.transfersData.value.isEmpty()){
                    binding.text.visibility=View.VISIBLE
                }else{
                    binding.transfersRecycler.adapter = adapter
                    binding.transfersRecycler.setHasFixedSize(true)
                    viewModel.transfersData.value.forEach {
                        println("id is ${it.id}")
                    }
                }
            }
        }
    }


}