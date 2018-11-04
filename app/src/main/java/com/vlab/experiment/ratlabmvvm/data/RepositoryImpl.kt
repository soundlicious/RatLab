package com.vlab.experiment.ratlabmvvm.data

import com.vlab.experiment.ratlabmvvm.data.local.db.TypicodeDao
import com.vlab.experiment.ratlabmvvm.data.models.typicode.*
import com.vlab.experiment.ratlabmvvm.data.network.TypicodeService
import io.reactivex.Single
import timber.log.Timber

class RepositoryImpl(private val service: TypicodeService, private val db: TypicodeDao) : Repository{
    override fun getUsers() : Single<List<UserModel>> = db.getUsers()
        .doOnSubscribe {Timber.i("Subscrime to local")}
        .filter { list -> !list.isEmpty() }
        .switchIfEmpty(service.fetchUsers()
            .doOnSubscribe { Timber.i("Subscribe to remote") }
            .doAfterSuccess { db.saveUser(it) })

    override fun getUserAlbums(userId : String) : Single<List<AlbumModel>> = db.getUserAlbums(userId)
        .doOnSubscribe {Timber.i("Subscrime to local")}
        .filter { list -> !list.isEmpty() }
        .switchIfEmpty(service.fetchUserAlbums(userId)
            .doOnSubscribe { Timber.i("Subscribe to remote") }
            .doAfterSuccess { db.saveAlbums(it) })

    override fun getUserPosts(userId: String) : Single<List<PostModel>> = db.getUserPost(userId)
        .doOnSubscribe {Timber.i("Subscrime to local")}
        .filter { list -> !list.isEmpty() }
        .switchIfEmpty(service.fetchUserPosts(userId)
            .doOnSubscribe { Timber.i("Subscribe to remote") }
            .doAfterSuccess { db.savePost(it) })

    override fun getUserPhotosAlbum(albumId: String) : Single<List<PhotoModel>> = db.getAlbumPhotos(albumId)
        .doOnSubscribe {Timber.i("Subscrime to local")}
        .filter { list -> !list.isEmpty() }
        .switchIfEmpty(service.fetchUserPhotos(albumId)
            .doOnSubscribe { Timber.i("Subscribe to remote") }
            .doAfterSuccess { db.savePhotos(it) })

    override fun getPostComment(postId: String) : Single<List<CommentModel>> = db.getPostComment(postId)
        .doOnSubscribe {Timber.i("Subscrime to local")}
        .filter { list -> !list.isEmpty() }
        .switchIfEmpty(service.fetchPostComments(postId)
            .doOnSubscribe { Timber.i("Subscribe to remote") }
            .doAfterSuccess { db.saveComments(it) })
}