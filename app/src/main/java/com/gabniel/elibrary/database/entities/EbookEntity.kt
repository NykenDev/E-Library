package com.gabniel.elibrary.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "ebook")
data class EbookEntity(
    @PrimaryKey
    val name: String,
    val image: String,
    val genre: String,
    val synopsis: String,
    val author: String,
): Parcelable
