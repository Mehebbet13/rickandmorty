package com.example.abbtechtestapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbtechtestapplication.models.Character
import com.example.abbtechtestapplication.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character>
        get() = _character

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterInfo(id)
            _character.postValue(response)
        }
    }
//    fun filterCharacter(name: String) {
//        viewModelScope.launch {
//            val response = repository.filterCharacter(name)
//            _character.postValue(response)
//        }
//    }
}