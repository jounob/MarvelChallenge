package com.esther.intermediachallenge.data.dataSource

import com.esther.intermediachallenge.data.models.NetResult
import com.esther.intermediachallenge.data.services.CharacterService
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val characterService: CharacterService
) : NetResult() {
    suspend fun getCharacter(auth: HashMap<String, String>, offset: Int, limit: Int) =
        getResult { characterService.getCharacters(auth, offset, limit) }

    suspend fun getComicsByCharacterID(characterId: Int,
        auth: HashMap<String, String>,
        offset: Int, limit: Int
    ) =
        getResult { characterService.getComicsByCharacterID(characterId, auth, offset, limit) }
}