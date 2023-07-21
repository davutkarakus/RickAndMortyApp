package com.davutkarakus.rickandmortyapp.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davutkarakus.rickandmortyapp.model.Characters
import com.davutkarakus.rickandmortyapp.repo.CharactersRepository
import com.davutkarakus.rickandmortyapp.service.CharactersApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: CharactersRepository,@ApplicationContext context: Context) : ViewModel() {
    // private val charactersApiService = CharactersApiService()
    // private val disposable = CompositeDisposable()

    val _characters = MutableLiveData<Characters>()
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
    fun isWifiEnabled(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return networkInfo?.isConnectedOrConnecting ?: false
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

    /*  fun refreshData() {
         getDataFromApi()
       }

       private fun getDataFromApi() {
           charactersLoading.value = true
           disposable.add(
               charactersApiService.getData()
                   .subscribeOn(Schedulers.newThread())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribeWith(object : DisposableSingleObserver<Characters>(){
                       override fun onSuccess(t: Characters) {
                           characters.value = t
                           charactersError.value = false
                           charactersLoading.value = false
                       }

                       override fun onError(e: Throwable) {
                           charactersError.value = true
                           charactersLoading.value = false
                           e.printStackTrace()
                       }
                   })
           )
       }

     */
}