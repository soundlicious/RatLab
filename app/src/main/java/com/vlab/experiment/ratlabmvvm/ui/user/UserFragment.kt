package com.vlab.experiment.ratlabmvvm.ui.user


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vlab.experiment.ratlabmvvm.R
import com.vlab.experiment.ratlabmvvm.UserActivity
import com.vlab.experiment.ratlabmvvm.core.BaseFragment
import com.vlab.experiment.ratlabmvvm.core.navigateTo
import com.vlab.experiment.ratlabmvvm.core.withModels
import com.vlab.experiment.ratlabmvvm.views.loadingRow
import com.vlab.experiment.ratlabmvvm.views.userItem
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.user_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFragment : BaseFragment() {

    private var disposeSearchView: Disposable? = null
    private val viewModel: UserViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUsers()
    }

    override fun onResume() {
        super.onResume()
        expandToolbar(true)
        (activity as UserActivity).SearchViewVisibility(View.VISIBLE)
        (activity as UserActivity).observeSearchBar(Consumer { text ->
            viewModel.search(text)
        })

    }

    private fun subscribeUsers() {
        viewModel.users.observe(this, Observer { userList ->
            recycler_view.withModels {
                userList.forEach { user ->
                    userItem {
                        id(user.id)
                        name(String.format(getString(R.string.userName), user.name, user.username))
                        clickListener(View.OnClickListener {
                            val direction =
                                UserFragmentDirections.actionUserFragmentToUserDetailsFragment().setUserId(user.id)
                            navigateTo(direction)
                        })
                    }
                }

                if (userList.isEmpty())
                    loadingRow {
                        id("loadingRow${userList.size}")
                        onBind { _, _, _ -> viewModel.updateUserList() }
                    }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        expandToolbar(false)
        (activity as UserActivity).SearchViewVisibility(View.GONE)
        disposeSearchView?.dispose()
    }
}
