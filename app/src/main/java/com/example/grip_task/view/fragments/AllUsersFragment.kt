
package com.example.grip_task.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.grip_task.databinding.FragmentUsersBinding
import com.example.grip_task.view.adapters.AllCustomersFragmentAdapter
import com.example.grip_task.viewmodel.DatabaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class AllUsersFragment : Fragment(){
    private lateinit var binding: FragmentUsersBinding
    private lateinit var viewModel: DatabaseViewModel
    private lateinit var adapter: AllCustomersFragmentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllCustomers().invokeOnCompletion{
            adapter = AllCustomersFragmentAdapter(viewModel.customersData.value)
            lifecycleScope.launch(Dispatchers.Main){
                binding.customersRecycler.adapter = adapter
                binding.customersRecycler.setHasFixedSize(true)
            }
        }
    }
}