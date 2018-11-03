package com.vlab.experiment.ratlabmvvm.ui.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vlab.experiment.ratlabmvvm.R
import com.vlab.experiment.ratlabmvvm.UserActivity
import com.vlab.experiment.ratlabmvvm.core.BaseFragment
import com.vlab.experiment.ratlabmvvm.data.models.typicode.PhotoModel
import kotlinx.android.synthetic.main.album_photos_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AlbumPhotosFragment : BaseFragment() {

    private val viewModel: UserDetailsViewModel by sharedViewModel()
    private val adapter: PhotoGridAdapter = PhotoGridAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setAlbumId(AlbumPhotosFragmentArgs.fromBundle(arguments).albumId)
        (activity as UserActivity).setUpNavController(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.album_photos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rV_albumPhoto.layoutManager = GridLayoutManager(context, 2)
        rV_albumPhoto.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        viewModel.updatePhotos()
        subscribePhotos()
    }

    private fun subscribePhotos() {
        viewModel.photos.observe(this, Observer { photos ->
            loading.visibility =
                    if (photos.isNullOrEmpty()) View.VISIBLE
                    else View.GONE
            adapter.updateList(photos)
        })
    }

    private class PhotoGridAdapter : RecyclerView.Adapter<PhotoGridAdapter.ViewHolder>() {

        private var photos: List<PhotoModel> = emptyList()

        internal inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val imageView: ImageView = view.findViewById(R.id.imageView_photoAlbum)
            fun bind(link: String) {
                Picasso.get()
                    .load(link)
                    .placeholder(R.drawable.ratexperiment)
                    .into(imageView)
            }
        }

        override fun getItemCount(): Int {
            return photos.size
        }

        override fun onBindViewHolder(holder: PhotoGridAdapter.ViewHolder, position: Int) {
            holder.bind(photos[position].thumbnailUrl)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo_album, parent, false)
            return ViewHolder(view)
        }

        fun updateList(list: List<PhotoModel>) {
            photos = list
            notifyDataSetChanged()
        }

    }

}

