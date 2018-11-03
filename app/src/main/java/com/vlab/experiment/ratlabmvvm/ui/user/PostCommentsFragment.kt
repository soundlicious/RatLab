package com.vlab.experiment.ratlabmvvm.ui.user

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vlab.experiment.ratlabmvvm.core.BaseFragment
import com.vlab.experiment.ratlabmvvm.core.withModels
import com.vlab.experiment.ratlabmvvm.views.commentItem
import com.vlab.experiment.ratlabmvvm.views.loadingRow
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PostCommentsFragment : BaseFragment(){

    private val viewModel:UserDetailsViewModel by sharedViewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val postId = PostCommentsFragmentArgs.fromBundle(arguments).postId
        viewModel.setPostId(postId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeComments()

    }

    private fun observeComments() {
        viewModel.comments.observe(this, Observer{comments ->
            recycler_view.withModels {
                if (comments.isEmpty()){
                    loadingRow {
                        id("loading")
                        onBind { _, _, _ -> viewModel.updateComments() }
                    }
                }

                comments.forEach { comment ->
                    commentItem {
                        id(comment.id)
                        username(comment.name)
                        mail(comment.email)
                        body(comment.body)
                    }
                }
            }
        })
    }


}