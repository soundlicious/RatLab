package com.vlab.experiment.ratlabmvvm.data

import com.vlab.experiment.ratlabmvvm.data.models.typicode.*
import io.reactivex.Single

interface Repository {
        fun getUsers() : Single<List<UserModel>>

        fun getUserAlbums(userId : String) : Single<List<AlbumModel>>

        fun getUserPosts(userId: String) : Single<List<PostModel>>

        fun getUserPhotosAlbum(albumId: String) : Single<List<PhotoModel>>

        fun getPostComment(postId: String) : Single<List<CommentModel>>
}