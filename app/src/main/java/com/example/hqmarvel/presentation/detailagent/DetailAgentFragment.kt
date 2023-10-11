package com.example.hqmarvel.presentation.detailagent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.hqmarvel.R
import com.example.hqmarvel.databinding.FragmentDetailAgentBinding

class DetailAgentFragment : Fragment() {

    private var description: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            description = it.getString("description")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailAgentBinding.inflate(inflater, container, false)

        binding.textDescription.text = description
        return binding.root
    }


}