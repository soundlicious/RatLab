package com.vlab.experiment.ratlabmvvm.di

import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.models.typicode.*
import com.vlab.experiment.ratlabmvvm.data.network.TypicodeService
import io.reactivex.Single

class TestRepository(private val service: TypicodeService): Repository {
    override fun getUsers(): Single<List<UserModel>> = service.fetchUsers()

    override fun getUserAlbums(userId: String): Single<List<AlbumModel>> = service.fetchUserAlbums(userId)

    override fun getUserPosts(userId: String): Single<List<PostModel>> = service.fetchUserPosts(userId)

    override fun getUserPhotosAlbum(albumId: String): Single<List<PhotoModel>> = service.fetchUserPhotos(albumId)

    override fun getPostComment(postId: String): Single<List<CommentModel>> = service.fetchPostComments(postId)
}