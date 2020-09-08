package com.zemoga.zemogatest.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.zemoga.zemogatest.db.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post_table")
    suspend fun getAll(): List<PostEntity>

    @Query("SELECT * FROM post_table")
    fun getAll3(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM post_table")
    fun getAll2(): List<PostEntity>

    @Query("SELECT * FROM post_table WHERE title LIKE :title")
    fun findByTitle2(title: String): PostEntity

    @Query("SELECT * FROM post_table WHERE title LIKE :title")
    fun findByTitle(title: String): LiveData<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(post: List<PostEntity>)

    @Delete
    fun delete(post: PostEntity)

    @Update
    fun updateTodo(vararg posts: PostEntity)

}
