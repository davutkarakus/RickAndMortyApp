package com.davutkarakus.rickandmortyapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.davutkarakus.rickandmortyapp.R

fun ImageView.downloadFromUrl(url:String?,progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.blur)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
fun placeholderProgressBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        this.strokeWidth = 8f
        this.centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(v:ImageView,url: String?) {
    v.downloadFromUrl(url, placeholderProgressBar(v.context))
}

@BindingAdapter("android:stringAppend")
fun stringAppend(t:TextView,episodes: List<String>?) {
    var newList : List<String>
    episodes?.let {
        newList = it
        for (i in newList) {
            newList = i.split("https://rickandmortyapi.com/api/episode/")
            for (a in newList) {
                t.append("${a} ")
            }
        }
    }

}

fun isWifiEnabled(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            }
        }
    return false
}

@BindingAdapter("android:onRefreshListener")
fun setOnRefreshListener(swipeRefreshLayout: SwipeRefreshLayout, onRefreshListener: () -> Unit) {
    swipeRefreshLayout.setOnRefreshListener {
        onRefreshListener.invoke()
    }
}

@BindingAdapter("android:refreshing")
fun isRefreshing(sRL:SwipeRefreshLayout,bool:Boolean){
    sRL.isRefreshing = bool
}