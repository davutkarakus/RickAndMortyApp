package com.davutkarakus.network.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
data class Location(
    @ColumnInfo(name = "location_name")
    @SerializedName("name")
    val name: String?,
    @ColumnInfo(name = "location_url")
    @SerializedName("url")
    val url: String?
)