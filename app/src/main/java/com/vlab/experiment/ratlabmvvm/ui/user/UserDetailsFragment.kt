package com.vlab.experiment.ratlabmvvm.ui.user

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.vlab.experiment.ratlabmvvm.R
import com.vlab.experiment.ratlabmvvm.core.BaseFragment
import com.vlab.experiment.ratlabmvvm.core.navigateTo
import com.vlab.experiment.ratlabmvvm.core.withModels
import com.vlab.experiment.ratlabmvvm.views.*
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class UserDetailsFragment : BaseFragment() {

    private val viewModel: UserDetailsViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUserId(UserDetailsFragmentArgs.fromBundle(arguments).userId)
        observerDetails()
    }

    private fun observerDetails() {
        viewModel.details.observe(this, Observer { pair ->
            recycler_view
                .withModels {

                sectionTitle {
                    id("SectionAlbum")
                    title(getString(R.string.albumsTitle))
                }
                val albumItems: MutableList<AlbumItemCarouselModel_> = arrayListOf()
                val albums = pair.first
                albums.forEach { album ->
                    albumItems.add(AlbumItemCarouselModel_()
                        .id(album.id)
                        .title(album.title)
                        .clickListener(View.OnClickListener {
                            val direction = UserDetailsFragmentDirections
                                .ActionUserDetailsFragmentToAlbumPhotosFragment()
                                .setAlbumId(album.id)
                            navigateTo(direction)
                        })
                    )
                }

                gridCarousel {
                    id("albumCarousel")
                    models(albumItems)
                }

                sectionTitle {
                    id("SectionPosts")
                    title(getString(R.string.postsTitle))
                }

                val posts = pair.second
                posts.forEach { post ->
                    postItem {
                        id(post.id)
                        title(post.title)
                        body(post.body)
                        clickListener(View.OnClickListener {
                            Timber.i("UserDetailFragment To PostComments - postId : ${post.id}")
                            navigateTo(UserDetailsFragmentDirections
                                .ActionUserDetailsFragmentToPostCommentsFragment()
                                .setPostId(post.id)
                            )
                        })
                    }
                }

                if (posts.isEmpty())
                    loadingRow {
                        id("loadingRow${posts.size}")
                        onBind { _, _, _ -> viewModel.updateDetail() }
                    }
            }
        })
    }

}
