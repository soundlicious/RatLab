package com.vlab.experiment.ratlabmvvm.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.vlab.experiment.ratlabmvvm.core.BaseFragment
import com.vlab.experiment.ratlabmvvm.core.withModels
import com.vlab.experiment.ratlabmvvm.views.loadingRow
import com.vlab.experiment.ratlabmvvm.views.userItem
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AlbumPhotosFragment: BaseFragment() {

    private val viewModel: UserDetailsViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.setAlbumId(AlbumPhotosFragmentArgs.fromBundle(arguments).albumId)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribePhotos()
    }

    private fun subscribePhotos() {
        viewModel.photos.observe(this, Observer {photos ->
            recycler_view.withModels {
                this.spanCount = 3
                photos.forEach { photo ->
                    userItem {
                        id(photo.id)
                        name(photo.url)
                    }
                }

                if (photos.isEmpty())
                    loadingRow {
                        id("LoadingRow")
                        onBind { _,_,_ -> viewModel.updatePhotos() }
                    }
            }
        })
    }
}