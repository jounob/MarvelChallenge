package com.esther.intermediachallenge.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.esther.intermediachallenge.databinding.FragmentCharactersBinding
import com.esther.intermediachallenge.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        setupCharactersList()
        characterObserver()
        setupPagination()

        return binding.root
    }



    private fun setupCharactersList() {
        binding.rvListComics.adapter = adapter
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
                it.isLoading -> {
                    loading()
                }
                it.isError -> {
                    retry()
                }
                it.isSuccess.isNotEmpty() -> {
                    adapter.addAll(it.isSuccess)
                    binding.progressBar.root.isVisible = false

                }
            }
        }

    }


    private fun loading() {
        binding.progressBar.root.isVisible = true
    }

    private fun retry() {
        val contextView = binding.rvListComics
        Snackbar.make(
            contextView,
            "No Internet Connection please retry",
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("Retry") {
                characterObserver()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}