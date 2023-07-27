package com.davutkarakus.rickandmortyapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davutkarakus.rickandmortyapp.model.Result
import com.davutkarakus.rickandmortyapp.repo.CharactersRepository
import com.davutkarakus.rickandmortyapp.util.isWifiEnabled
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: CharactersRepository,@ApplicationContext context: Context) : ViewModel() {
    private val _characters = MutableLiveData<List<Result>>()
    val charactersError = MutableLiveData<Boolean>()
    val charactersLoading = MutableLiveData<Boolean>()
    val characters:LiveData<List<Result>>
    get() = _characters
    init {
        getAllCharacters(context)
    }
        private fun getAllCharacters(context: Context) = viewModelScope.launch {
        charactersLoading.value = true
        if(repository.getAllCharactersDao().isEmpty()) {
            getDataFromApi(context)
        }else {
            getDataFromDao(context)
        }
    }
     fun getDataFromApi(context: Context) = viewModelScope.launch{
         charactersLoading.value = true
         charactersError.value = false
         if(isWifiEnabled(context)){
             repository.getAllCharacters().let {response->
                 if(response.isSuccessful){
                     _characters.postValue(response.body()?.results ?: listOf())
                     deleteAllCharacters()
                     response.body()?.results?.let {
                         insertAllCharacters(it)
                     }
                     Toast.makeText(context,"From Api",Toast.LENGTH_LONG).show()
                     charactersLoading.value = false
                 }else{
                     charactersLoading.value = false
                     charactersError.value = true
                     Log.i("FeedViewModel","Error!")
                 }
             }
         }else {
             delay(1000)
             charactersLoading.value = false
             charactersError.value = true
             Log.i("FeedViewModel","Internet Connection Problem!")
         }
    }
    fun insertAllCharacters(list:List<Result>) = viewModelScope.launch {
        repository.insertCharactersDao(*list.toTypedArray())
    }
    fun deleteAllCharacters() = viewModelScope.launch {
        repository.deleteAllCharactersDao()
    }
    fun getDataFromDao(context: Context) = viewModelScope.launch (Dispatchers.Main) {
        _characters.postValue(repository.getAllCharactersDao())
        println(repository.getAllCharactersDao())
        Toast.makeText(context,"From Room",Toast.LENGTH_LONG).show()
        charactersLoading.value = false
    }
}