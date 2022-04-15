package com.esther.intermediachallenge.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esther.intermediachallenge.data.models.Character
import com.esther.intermediachallenge.data.models.Resource
import com.esther.intermediachallenge.data.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {
    private val _charactersState = MutableLiveData<CharacterState>()
    val characterState: LiveData<CharacterState> = _charactersState
    private var moreCharacter =0

    init {
        loadCharacters(moreCharacter)
    }

    private fun loadCharacters(moreCharacter: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                charactersRepository.getCharacters(moreCharacter)
            }.run {
                when (status) {
                    Resource.Status.LOADING ->
                        _charactersState.value = CharacterState(isLoading = true)
                    Resource.Status.ERROR ->
                        _charactersState.value = CharacterState(isError = true)
                    Resource.Status.SUCCESS ->
                        _charactersState.value = CharacterState(isSuccess = this.data?: emptyList())
                }
            }
        }
    }

    data class CharacterState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val isSuccess: List<Character> = emptyList()
    )


    fun loadMoreCharacters() {
         moreCharacter += 15
        loadCharacters(moreCharacter)

    }
}

//private fun loadCharacters(offset: Int) {
//    viewModelScope.launch {
//        when (val response = charactersRepository.getCharacters(offset)) {
//            is NetResult.Success -> {
//                _characters.postValue(response.data.charactersList.characters)
//            }
//            is NetResult.Error -> {
//                // TODO complete
//            }
//        }
//    }
//}