package com.zemoga.zemogatest.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
class PostEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

//    @ColumnInfo(name = "id")
//    val userId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "body")
    val body: String

)