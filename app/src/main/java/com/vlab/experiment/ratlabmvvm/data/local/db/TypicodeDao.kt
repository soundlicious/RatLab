package com.vlab.experiment.ratlabmvvm.data.local.db

import com.vlab.experiment.ratlabmvvm.data.models.typicode.*
import io.reactivex.Maybe

import androidx.annotation.WorkerThread
import androidx.room.*


@Dao
interface TypicodeDao {

    @Query("SELECT * from UserModel")
    fun getUsers() : Maybe<List<UserModel>>

    @Query("SELECT * from AlbumModel WHERE userId == :id")
    fun getUserAlbums(id: String) : Maybe<List<AlbumModel>>

    @Query("SELECT * from PostModel WHERE userId == :id")
    fun getUserPost(id: String) : Maybe<List<PostModel>>

    @Query("SELECT * from PhotoModel WHERE albumId == :id")
    fun getAlbumPhotos(id: String) : Maybe<List<PhotoModel>>

    @Query("SELECT * from CommentModel WHERE postId == :id")
    fun getPostComment(id: String) : Maybe<List<CommentModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    fun saveUser(items: List<UserModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    fun saveAlbums(items: List<AlbumModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    fun savePost(items: List<PostModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    fun savePhotos(items: List<PhotoModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    fun saveComments(items: List<CommentModel>)
}