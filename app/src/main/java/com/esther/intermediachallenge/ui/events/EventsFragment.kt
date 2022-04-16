package com.esther.intermediachallenge.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.esther.intermediachallenge.R
import com.esther.intermediachallenge.databinding.FragmentEventsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding ?= null
    private val binding get() = _binding!!
    private val viewModel: EventViewModel by viewModels()
    private val adapter = EventAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEventsBinding.inflate(inflater,container,false)
        setupEventList()
        eventObserve()


        return binding.root
    }

    private fun setupEventList(){

    }

    private fun eventObserve(){
        viewModel.eventState.observe(viewLifecycleOwner, Observer {
            when{
                it.isLoading->{}
                it.isError->{}
                it.isSuccess.isNotEmpty() -> {
                    adapter.addAll(it.isSuccess)
                    binding.rvEventsList.adapter = adapter
                }
            }
        })

    }

}