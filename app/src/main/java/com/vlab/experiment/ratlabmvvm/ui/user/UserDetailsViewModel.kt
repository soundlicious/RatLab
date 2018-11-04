package com.vlab.experiment.ratlabmvvm.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.RepositoryImpl
import com.vlab.experiment.ratlabmvvm.data.models.typicode.AlbumModel
import com.vlab.experiment.ratlabmvvm.data.models.typicode.CommentModel
import com.vlab.experiment.ratlabmvvm.data.models.typicode.PhotoModel
import com.vlab.experiment.ratlabmvvm.data.models.typicode.PostModel
import com.vlab.experiment.ratlabmvvm.utils.rx.SchedulerProvider
import com.vlab.experiment.ratlabmvvm.utils.rx.with
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import timber.log.Timber

class UserDetailsViewModel(private val repository: Repository, private val schedulers: SchedulerProvider) : ViewModel() {

    val details: MutableLiveData<Pair<List<AlbumModel>, List<PostModel>>> = MutableLiveData()
    val photos: MutableLiveData<List<PhotoModel>> = MutableLiveData()
    val comments: MutableLiveData<List<CommentModel>> = MutableLiveData()

    private val albumId: MutableLiveData<String> = MutableLiveData()
    private val userId: MutableLiveData<String> = MutableLiveData()
    private val postId: MutableLiveData<String> = MutableLiveData()

    private var dispose: Disposable? = null

    init {
        details.value = Pair(emptyList(), emptyList())
        photos.value = emptyList()
        comments.value = emptyList()
        userId.value = ""
        albumId.value = ""
        postId.value = ""
    }

    fun setUserId(id: String) {
        val oldId = userId.value
        if (oldId.isNullOrEmpty() || oldId != id) {
            userId.value = id
            details.value = Pair(emptyList(), emptyList())
        }
    }

    fun setAlbumId(id: String) {
        Timber.i("albumId is : $id")
        val oldId = albumId.value
        if (oldId.isNullOrEmpty() || oldId != id) {
            albumId.value = id
            photos.value = emptyList()
        }
    }

    fun setPostId(id: String){
        val oldId = postId.value
        if (oldId.isNullOrEmpty() || oldId != id) {
            postId.value = id
            comments.value = emptyList()
        }
    }

    fun updateDetail() {
        val id = userId.value
        if (!id.isNullOrBlank()) {
            val obs1 = repository.getUserAlbums(id)
            val obs2 = repository.getUserPosts(id)
            dispose = Single.zip(obs1,
                obs2,
                BiFunction<List<AlbumModel>, List<PostModel>, Pair<List<AlbumModel>, List<PostModel>>> { albumList, postList ->
                    Pair(albumList, postList)
                })
                .with(schedulers)
                .subscribe(this::onPairReceived, this::onFetchError)
        }
    }

    fun updatePhotos() {
        val id = albumId.value
        if (!id.isNullOrBlank()) {
            dispose = repository.getUserPhotosAlbum(id)
                .with(schedulers)
                .subscribe(this::onPhotoReceived, this::onFetchError)
        }
    }

    fun updateComments() {
        val id = postId.value
        if (!id.isNullOrBlank())
            dispose = repository.getPostComment(id)
                .with(schedulers)
                .subscribe(this::onCommentReceived, this::onFetchError)
    }

    private fun onPairReceived(pair: Pair<List<AlbumModel>, List<PostModel>>) {
        details.value = pair
        dispose?.dispose()
    }

    private fun onPhotoReceived(photoList: List<PhotoModel>) {
        photos.value = photoList
        dispose?.dispose()
    }

    private fun onCommentReceived(commentList: List<CommentModel>) {
        comments.value = commentList
        dispose?.dispose()
    }

    private fun onFetchError(throwable: Throwable) {
        Timber.i(throwable)
        dispose?.dispose()
    }

}

