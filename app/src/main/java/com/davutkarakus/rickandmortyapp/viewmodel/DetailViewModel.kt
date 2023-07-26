package com.davutkarakus.rickandmortyapp.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davutkarakus.rickandmortyapp.model.Result
import com.davutkarakus.rickandmortyapp.repo.CharactersRepository
import com.davutkarakus.rickandmortyapp.util.isWifiEnabled
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private fun getCharacter(id: Int) = viewModelScope.launch {
        charLoading.value = true
        repo.getCharacter(id).let { response ->
            if(response.isSuccessful){
                _char.postValue(response.body())
                charLoading.value = false
            }else {
                charLoading.value = false
                charError.value = true
                Log.i("DetailViewModel","Error!")
            }
        }
    }
}