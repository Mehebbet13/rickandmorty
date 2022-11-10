package com.example.abbtechtestapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.abbtechtestapplication.models.Character
import com.example.abbtechtestapplication.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AllCharactersViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {
    val list = repository.getQuotes().cachedIn(viewModelScope)

    fun search(name:String):LiveData<PagingData<Character>>{
       return repository.getQuotes(name).cachedIn(viewModelScope)

    }    }

