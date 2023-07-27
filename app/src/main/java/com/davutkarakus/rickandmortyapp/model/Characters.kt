package com.davutkarakus.rickandmortyapp.model

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Result>?
)