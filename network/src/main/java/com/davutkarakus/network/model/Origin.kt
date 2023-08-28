package com.davutkarakus.network.model
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Origin(
    @ColumnInfo(name = "origin_name")
    @SerializedName("name")
    val name: String?,
    @ColumnInfo(name = "origin_url")
    @SerializedName("url")
    val url: String?
)