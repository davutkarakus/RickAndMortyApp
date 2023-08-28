package com.davutkarakus.rickandmortyapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davutkarakus.network.model.Result
import com.davutkarakus.rickandmortyapp.repo.CharactersRepository
import com.davutkarakus.rickandmortyapp.util.isWifiEnabled
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo:CharactersRepository) : ViewModel() {
    val _char = MutableLiveData<Result>()
    val charLoading = MutableLiveData<Boolean>()
    val charError = MutableLiveData<Boolean>()

    fun getData(id:Int,context: Context) {
        charLoading.value = true
        if (isWifiEnabled(context)){
            getCharacter(id)
        }else {
            charLoading.value = false
            charError.value = true
        }
    }
    private fun getCharacter(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        charLoading.postValue(true)
        repo.getCharacter(id).let { response ->
            if(response.isSuccessful){
                _char.postValue(response.body())
                charLoading.postValue(false)
            }else {
                charLoading.postValue(false)
                charError.postValue(true)
                Log.i("DetailViewModel","Error!")
            }
        }
    }
    //Detay sayfası apiden değil de roomdan çekilmek istenirse.Aşağıdaki fonksiyon kullanılabilir.

    /*private fun getCharacterFromRoom(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        val char = repo.getCharacterFromRoom(id)
        _char.postValue(char)
        charLoading.postValue(false)
    }
     */
}