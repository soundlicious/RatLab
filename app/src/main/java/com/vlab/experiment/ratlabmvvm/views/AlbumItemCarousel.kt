package com.vlab.experiment.ratlabmvvm.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.vlab.experiment.ratlabmvvm.R
import kotlinx.android.synthetic.main.item_album_carousel.view.*

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class AlbumItemCarousel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.item_album_carousel, this)
        imageView_AlbumThumbnail.setImageResource(R.drawable.ratexperiment)

    }

    @TextProp
    fun setTitle(title: CharSequence) {
        textView_albumTitle.text = title
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        cardView.setOnClickListener(clickListener)
        textView_albumTitle.setOnClickListener(clickListener)
    }
}