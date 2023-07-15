package com.davutkarakus.rickandmortyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.davutkarakus.rickandmortyapp.model.Result
import com.davutkarakus.rickandmortyapp.service.CharacterDetailApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {
    private val characterDetailApiService = CharacterDetailApiService()
    private val disposable = CompositeDisposable()

    val char = MutableLiveData<Result>()
    val charLoading = MutableLiveData<Boolean>()
    val charError = MutableLiveData<Boolean>()

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
}