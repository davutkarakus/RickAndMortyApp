package com.davutkarakus.rickandmortyapp.util

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
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