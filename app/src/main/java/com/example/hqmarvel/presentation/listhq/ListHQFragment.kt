package com.example.hqmarvel.presentation.listhq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hqmarvel.R
import com.example.hqmarvel.data.DataState
import com.example.hqmarvel.databinding.FragmentHQBinding
import com.example.hqmarvel.presentation.listhq.adapter.ItemListener
import com.example.hqmarvel.presentation.listhq.adapter.ListAdapter


class ListHQFragment : Fragment(), ItemListener {

    private val viewModel by navGraphViewModels<HQViewModel>(R.id.nav_graph) { defaultViewModelProviderFactory }
    private lateinit var adapter: ListAdapter
    private lateinit var binding: FragmentHQBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHQBinding.inflate(inflater)
        val recycler = binding.recyclerview

        //OUTRA FORMA DE INSTANCIAR O ADAPTER
        //binding.recycler.layoutManager = LinearLayoutManager(this) //layout Recycler
        //binding.recycler.adapter = adapter  //Adapter

        adapter = ListAdapter(this)
        recycler.apply {
            this.adapter = this@ListHQFragment.adapter
            this.layoutManager = LinearLayoutManager(context)
        }


        initObservers()

        return binding.root
    }

    override fun onItemSelected(position: Int) {
        viewModel.onSelected(position)
    }


    private fun initObservers(){
        viewModel.listLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateData(it)
            }
        })

        viewModel.navigationToDetailLiveData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                val action = R.id.action_listHQFragment_to_detailsFragment
                findNavController().navigate(action)
            }
        })

        viewModel.appState.observe(viewLifecycleOwner, Observer {
            with(binding){
                recyclerview.visibility =  if(it == DataState.Success) VISIBLE else GONE
                linearLoading.visibility = if(it == DataState.Loading) VISIBLE else GONE
                linearError.visibility = if(it == DataState.Error) VISIBLE else GONE
            }
        })
    }


}