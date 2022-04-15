package com.esther.intermediachallenge.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.esther.intermediachallenge.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding ?= null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        setupCharactersList()
        characterObserver()
        setupPagination()
        return binding.root
    }

    private fun setupCharactersList() {

        adapter.onClickListener = {
          val action = CharactersFragmentDirections.goToDetails(it)
            findNavController().navigate(action)
        }
    }

    private fun setupPagination() {
        binding.rvListComics.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreCharacters()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun characterObserver() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            when {
                it.isLoading -> {}
                it.isError -> {}
                it.isSuccess.isNotEmpty() -> {
                    adapter.addAll(it.isSuccess)
                    binding.rvListComics.adapter = adapter
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}