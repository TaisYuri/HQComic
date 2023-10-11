package com.example.hqmarvel.presentation.listagent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hqmarvel.R
import com.example.hqmarvel.data.model.response.AgentData
import com.example.hqmarvel.data.DataState
import com.example.hqmarvel.databinding.FragmentAgentListBinding
import com.example.hqmarvel.presentation.listagent.adapter.AgentListAdapter

class AgentListFragment : Fragment() {
    private val viewModel: AgentListViewModel by viewModels()
    private lateinit var binding: FragmentAgentListBinding
    private lateinit var adapter: AgentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgentListBinding.inflate(inflater, container, false)

        adapter = AgentListAdapter { onClickItem(it) }
        binding.recyclerAgent.apply {
            this.adapter = this@AgentListFragment.adapter
            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        initAgentListObserver()

        return binding.root
    }

    private fun onClickItem(agent: AgentData) {
        val bundle = Bundle().apply {
            putString("description", agent.description)
        }

        findNavController().navigate(R.id.detailAgentFragment, bundle)
    }

    private fun initAgentListObserver() {
        viewModel.listAgentLiveData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateData(it)
            }
        }

        viewModel.appState.observe(viewLifecycleOwner) {
            with(binding) {
                recyclerAgent.visibility = if (it == DataState.Success) View.VISIBLE else View.GONE
                linearLoading.visibility = if (it == DataState.Loading) View.VISIBLE else View.GONE
                linearError.visibility = if (it == DataState.Error) View.VISIBLE else View.GONE
            }
        }
    }


}