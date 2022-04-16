package com.esther.intermediachallenge.data.repositories

import com.esther.intermediachallenge.data.dataSource.CharacterDataSource
import com.esther.intermediachallenge.data.models.Character
import com.esther.intermediachallenge.data.models.Resource
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val characterDataSource: CharacterDataSource
) : BaseRepository() {

    suspend fun getCharacters(offset: Int = 0, limit: Int = 15): Resource<List<Character>> =
        characterDataSource.getCharacter(authParams.getMap(), offset, limit)

    suspend fun getComicsByCharacterID(characterId:Int, offset: Int = 0, limit: Int = 10) =
        characterDataSource.getComicsByCharacterID(characterId, authParams.getMap(), offset, limit)
}