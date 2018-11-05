package com.vlab.experiment.ratlabmvvm.data.network

import com.vlab.experiment.ratlabmvvm.data.models.typicode.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface TypicodeService {

    @Headers("Accept: application/json")
    @GET("/users")
    fun fetchUsers(): Single<List<UserModel>>

    @Headers("Accept: application/json")
    @GET("/users/{userId}/posts")
    fun fetchUserPosts(@Path("userId") userId: String): Single<List<PostModel>>

    @Headers("Accept: application/json")
    @GET("/users/{userId}/albums")
    fun fetchUserAlbums(@Path("userId") userId: String): Single<List<AlbumModel>>

    @Headers("Accept: application/json")
    @GET("/albums/{albumId}/photos")
    fun fetchUserPhotos(@Path("albumId")albumId: String): Single<List<PhotoModel>>

    @Headers("Accept: application/json")
    @GET("/posts/{postId}/comments")
    fun fetchPostComments(@Path("postId") postId: String) : Single<List<CommentModel>>

    @Headers("Accept: application/json")
    @POST("/posts/{postId}/comments")
    fun sendPostComments(@Path("postId") postId: String) : Single<List<CommentModel>>
}