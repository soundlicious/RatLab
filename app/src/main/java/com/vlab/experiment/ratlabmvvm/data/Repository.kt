package com.vlab.experiment.ratlabmvvm.data

import com.vlab.experiment.ratlabmvvm.data.models.typicode.UserModel
import com.vlab.experiment.ratlabmvvm.data.network.TypicodeService
import io.reactivex.Single

class Repository(private val service: TypicodeService){
    fun getUsers() : Single<List<UserModel>> = service.fetchUsers()

    fun getUserAlbums(userId : String) = service.fetchUserAlbums(userId)

    fun getUserPosts(userId: String) = service.fetchUserPosts(userId)

    fun getUserPhotosAlbum(albumId: String) = service.fetchUserPhotos(albumId)

    fun getPostComment(postId: String) = service.fetchPostComments(postId)
}