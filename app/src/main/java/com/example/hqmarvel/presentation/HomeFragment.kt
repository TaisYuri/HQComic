package com.example.hqmarvel.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hqmarvel.R
import com.example.hqmarvel.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.buttonHq.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_listHQFragment)
        }

        binding.buttonAgents.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_agentListFragment)
        }

        return binding.root
    }


}