package com.esther.intermediachallenge.ui.events

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.esther.intermediachallenge.databinding.FragmentEventsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventViewModel by viewModels()
    private val adapter = EventAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        setupEventList()
        eventObserve()
        return binding.root
    }

    private fun setupEventList() {
        binding.rvEventsList.adapter = adapter
    }

    private fun eventObserve() {
        viewModel.eventState.observe(viewLifecycleOwner) {
            when {
                it.isLoading -> {loading()}
                it.isError -> {retry()}
                it.isSuccess.isNotEmpty() -> {
                    adapter.addAll(it.isSuccess)
                    binding.progressBarEvent.root.isVisible = false
                }
            }
        }
    }
    private fun loading(){
        binding.progressBarEvent.root.isVisible = true
    }

    private fun retry(){
        val contextView = binding.rvEventsList
        Snackbar.make(contextView, "No Internet Connection please retry", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                eventObserve()
            }
            .show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}