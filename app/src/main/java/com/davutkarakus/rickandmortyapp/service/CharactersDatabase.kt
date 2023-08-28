package com.davutkarakus.rickandmortyapp.service

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davutkarakus.network.model.ListTypeConverter
import com.davutkarakus.network.model.Result

@Database(entities = [Result::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun charactersDao() : CharactersDao
}