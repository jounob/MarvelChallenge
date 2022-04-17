package com.esther.intermediachallenge.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.esther.intermediachallenge.R
import com.esther.intermediachallenge.databinding.FragmentCharacterDetailsBinding
import com.esther.intermediachallenge.ui.characters.CharactersAdapter
import com.esther.intermediachallenge.ui.characters.CharactersFragmentArgs
import com.esther.intermediachallenge.ui.main.MainActivity
import com.esther.intermediachallenge.utils.binding.setImage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private val adapter = ComicAdapter()

    private val args by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        setupView()
        characterDetailsObserver()

        return binding.root
    }

    override fun onDestroyView() {
        val mainActivity = activity as MainActivity
        super.onDestroyView()
        mainActivity.setToolbarTitle()
        mainActivity.buttonNavigationVisible()
        _binding = null
    }

    private fun setupView() {
        val mainActivity = activity as MainActivity
        mainActivity.buttonNavigationHide()
        mainActivity.setToolbarTitle(args.character.name)
        with(binding) {
            setImage(ivImageComcis, args.character.thumbnail)
            txtViewDescription.text = args.character.description
            rvComics.adapter = adapter

        }
        viewModel.loadComics(args.character.id)

    }
    private fun characterDetailsObserver() {
        viewModel.characterDetailsState.observe(viewLifecycleOwner) {
            when {
                it.isLoading -> {
                    hideViewAndShowLoading()
                }
                it.isError -> {
                    retry()
                }
                it.isSuccess.isNotEmpty() -> {
                    adapter.addAll(it.isSuccess)
                    hideLoadingAndShowView()
                }
            }
        }
    }

    private fun retry(){
        val contextView = binding.rvComics
        Snackbar.make(contextView, "Wait a fwe second and try again", Snackbar.LENGTH_LONG)
            .setAction("RETRY") {
                // Responds to click on the action
                characterDetailsObserver()
            }
            .show()
    }

    private fun hideViewAndShowLoading(){
        binding.progressBar.root.isVisible = true
        binding.layoutDetails.isVisible = false
    }
    private fun hideLoadingAndShowView(){
        binding.progressBar.root.isVisible = false
        binding.layoutDetails.isVisible = true
    }


}