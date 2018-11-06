package com.vlab.experiment.ratlabmvvm.data.network

import com.vlab.experiment.ratlabmvvm.data.models.typicode.*
import io.reactivex.Single

class TypicodeTestImpl(var success: Boolean = true) : TypicodeService{

    private val posts : List<PostModel> = listOf(
        PostModel(1, "1","title1", "body1"),
        PostModel(1, "2","title2", "body2"))
    private val albums = listOf(
        AlbumModel("1", "1", "title1"),
        AlbumModel("1", "2", "title2"))
    private val photos = listOf(
        PhotoModel("1", "1", "title1", "url1", "thumbnailUrl1"),
        PhotoModel("1", "2", "title2", "url2", "thumbnailUrl2")
    )
    private val comments = listOf(
        CommentModel("1", "1", "name1", "email1", "body1"),
        CommentModel("1", "2", "name2", "email2", "body3")
    )
    private val users = listOf(
        UserModel("1", "name1", "usrname1", "email1", "phone1", "website1"),
        UserModel("2", "name2", "usrname2", "email2", "phone2", "website2")
    )
    override fun fetchUserPosts(userId: String): Single<List<PostModel>> = Single.create { emitter ->
        if (success)
            emitter.onSuccess(posts)
        else
            emitter.onError(Throwable("404"))
    }

    override fun fetchUserAlbums(userId: String): Single<List<AlbumModel>> = Single.create { albums }

    override fun fetchUserPhotos(albumId: String): Single<List<PhotoModel>> = Single.create { photos }

    override fun fetchPostComments(postId: String): Single<List<CommentModel>> = Single.create { comments }

    override fun sendPostComment(postId: String): Single<CommentModel> = Single.create { comments[0] }

    override fun fetchUsers(): Single<List<UserModel>> = Single.create { emitter ->
        if (success)
            emitter.onSuccess(users)
        else
            emitter.onError(Throwable("404"))
    }
}