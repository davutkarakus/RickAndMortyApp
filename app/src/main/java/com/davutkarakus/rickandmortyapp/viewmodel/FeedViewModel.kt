package com.davutkarakus.rickandmortyapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davutkarakus.rickandmortyapp.model.Characters
import com.davutkarakus.rickandmortyapp.repo.CharactersRepository
import com.davutkarakus.rickandmortyapp.util.isWifiEnabled
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: CharactersRepository,@ApplicationContext context: Context) : ViewModel() {

    private val _characters = MutableLiveData<Characters>()
    val charactersError = MutableLiveData<Boolean>()
    val charactersLoading = MutableLiveData<Boolean>()
    val characters:LiveData<Characters>
    get() = _characters

    init {
        charactersLoading.value = true
        if (isWifiEnabled(context)) {
            getAllCharacters()
        }else {
            charactersLoading.value = false
            charactersError.value = true
        }
    }

    private fun getAllCharacters() = viewModelScope.launch {
        charactersLoading.value = true
        repository.getAllCharacters().let { response ->
            if(response.isSuccessful){
                _characters.postValue(response.body())
                charactersLoading.value = false
            }else{
                charactersLoading.value = false
                charactersError.value = true
                Log.i("FeedViewModel","Error!")
            }
        }
    }
}