package com.davutkarakus.rickandmortyapp.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davutkarakus.rickandmortyapp.model.Characters
import com.davutkarakus.rickandmortyapp.model.Result
import com.davutkarakus.rickandmortyapp.repo.CharactersRepository
import com.davutkarakus.rickandmortyapp.service.CharacterDetailApiService
import com.davutkarakus.rickandmortyapp.service.CharactersApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo:CharactersRepository) : ViewModel() {
    private val characterDetailApiService = CharacterDetailApiService()
    private val disposable = CompositeDisposable()
    val _char = MutableLiveData<Result>()
    val charLoading = MutableLiveData<Boolean>()
    val charError = MutableLiveData<Boolean>()
    val char:LiveData<Result>
    get() = _char

    fun getData(id:Int,context: Context) {
        charLoading.value = true
        if (isWifiEnabled(context)){
            getCharacter(id)
        }else {
            charLoading.value = false
            charError.value = true
        }
    }
    fun isWifiEnabled(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return networkInfo?.isConnectedOrConnecting ?: false
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


/*
    fun refreshData(id:Int){
        getCharFromApi(id)
    }
    private fun getCharFromApi(id:Int) {
        charLoading.value = true
        disposable.add(
            characterDetailApiService.getChar(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Result>() {
                    override fun onSuccess(t: Result) {
                        char.value = t
                        charError.value = false
                        charLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        charError.value = true
                        charLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

 */
}