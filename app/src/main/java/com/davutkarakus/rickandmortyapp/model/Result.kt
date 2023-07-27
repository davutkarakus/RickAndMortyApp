package com.davutkarakus.rickandmortyapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "results")
data class Result(
    @ColumnInfo(name = "created")
    @SerializedName("created")
    val created: String?,
    @ColumnInfo(name = "episodes")
    @SerializedName("episode")
    val episode: List<String>?,
    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    val gender: String?,
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String?,
    @Embedded
    @SerializedName("location")
    val location: Location?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,
    @Embedded
    @SerializedName("origin")
    val origin: Origin?,
    @ColumnInfo(name = "species")
    @SerializedName("species")
    val species: String?,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    val status: String?,
    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String?,
    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

