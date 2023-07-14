package com.davutkarakus.rickandmortyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davutkarakus.rickandmortyapp.model.Characters
import com.davutkarakus.rickandmortyapp.model.Result
import com.davutkarakus.rickandmortyapp.service.CharactersApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {
    private val charactersApiService = CharactersApiService()
    private val disposable = CompositeDisposable()

    val characters = MutableLiveData<Characters>()
    val charactersError = MutableLiveData<Boolean>()
    val charactersLoading = MutableLiveData<Boolean>()
    fun refreshData() {
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
}