package com.vlab.experiment.ratlabmvvm.views

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(saveViewState = true, autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class GridCarousel(context: Context) : Carousel(context) {

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false)
    }

}