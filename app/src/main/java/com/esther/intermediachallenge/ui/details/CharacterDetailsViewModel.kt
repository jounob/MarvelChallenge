package com.esther.intermediachallenge.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esther.intermediachallenge.data.models.Comic
import com.esther.intermediachallenge.data.models.Resource
import com.esther.intermediachallenge.data.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel() {
    private val _characterDetailsState = MutableLiveData<CharacterDetailsState>()
    val characterDetailsState: LiveData<CharacterDetailsState> = _characterDetailsState
//    init {
//        loadComics(22506)
//    }
    fun loadComics(characterId: Int) {
    _characterDetailsState.value = CharacterDetailsState(isLoading = true)
    viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                charactersRepository.getComicsByCharacterID(characterId)
            }.run {
                when (status) {
                    Resource.Status.LOADING ->
                        _characterDetailsState.value = CharacterDetailsState(isLoading = true)
                    Resource.Status.ERROR ->
                        _characterDetailsState.value = CharacterDetailsState(isError = true)
                    Resource.Status.SUCCESS ->
                        _characterDetailsState.value =
                            CharacterDetailsState(isSuccess = this.data?: emptyList())
                }
            }
        }
    }

    data class CharacterDetailsState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val isSuccess: List<Comic> = emptyList()
    )
}