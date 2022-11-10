package com.example.abbtechtestapplication.repository

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.abbtechtestapplication.api.RickAndMortyApi
import com.example.abbtechtestapplication.paging.CharacterPagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val characterAPI: RickAndMortyApi) {

    fun getQuotes(name:String? = null) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { CharacterPagingSource(characterAPI,name?:"") }
    ).liveData
//    val filterFlow = MutableStateFlow<String>("")
    fun pagingDataFlow(filterFlow:MutableStateFlow<String>) = filterFlow.flatMapLatest { filter ->
        Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100),
            pagingSourceFactory = { CharacterPagingSource(characterAPI,filter)}).flow
    }.asLiveData()

    suspend fun getCharacterInfo(id: Int) = characterAPI.getCharacterById(id)
    suspend fun filterCharacter(name: String) =
        characterAPI.filterCharacter(name)

}