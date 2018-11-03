package com.vlab.experiment.ratlabmvvm.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.models.typicode.UserModel
import com.vlab.experiment.ratlabmvvm.utils.rx.SchedulerProvider
import com.vlab.experiment.ratlabmvvm.utils.rx.with
import io.reactivex.disposables.Disposable
import timber.log.Timber

class UserViewModel(private val repository: Repository, private val scheduler: SchedulerProvider) : ViewModel() {

    val users: MutableLiveData<List<UserModel>> = MutableLiveData()
    val query: MutableLiveData<String?> = MutableLiveData()
    private val userList: MutableLiveData<List<UserModel>> = MutableLiveData()
    val error: LiveData<String?> = MutableLiveData()

    private var dispose: Disposable? = null

    init {
        userList.value = emptyList()
        users.value = emptyList()
        query.value = null
    }

    private fun onListReceived(list: List<UserModel>) {
        userList.value = list
        users.value = list.filter(this::filterUserList)
        dispose?.dispose()
    }

    private fun onError(throwable: Throwable) {
        Timber.i(throwable)
        dispose?.dispose()
    }

    fun updateUserList(){
        dispose = repository.getUsers()
            .with(scheduler)
            .subscribe(this::onListReceived, this::onError)
    }

    private fun filterUserList(userModel: UserModel): Boolean {
        val search =  query.value
        return if(query.value == null) true
        else userModel.name.startsWith(search!!, ignoreCase = true)
}

    fun search(text: String?) {
        query.value = text
        users.value = userList.value?.filter(this::filterUserList)
    }
}
