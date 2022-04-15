package com.esther.intermediachallenge.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.esther.intermediachallenge.R
import com.esther.intermediachallenge.databinding.FragmentCharacterDetailsBinding
import com.esther.intermediachallenge.ui.characters.CharactersFragmentArgs
import com.esther.intermediachallenge.ui.main.MainActivity
import com.esther.intermediachallenge.utils.binding.setImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    //    private val viewModel: CharacterDetailsViewModel by viewModels()
//    private val adapter = CharacterDetailsAdapter()
    private val args by navArgs<CharacterDetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        setupView()


        return binding.root
    }

    override fun onDestroyView() {
        val mainActivity = activity as MainActivity
        super.onDestroyView()
        mainActivity.setToolbarTitle()
        _binding = null
    }

    private fun setupView() {
        val mainActivity = activity as MainActivity

        mainActivity.setToolbarTitle(args.character.name)
        with(binding) {
            setImage(ivImageComcis, args.character.thumbnail)
            txtViewDescription.text = args.character.description


        }
    }
}