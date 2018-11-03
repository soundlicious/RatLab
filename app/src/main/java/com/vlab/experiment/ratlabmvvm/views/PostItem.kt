package com.vlab.experiment.ratlabmvvm.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.vlab.experiment.ratlabmvvm.R
import kotlinx.android.synthetic.main.item_post.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PostItem @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_post, this).setPadding(8,8,8,8)
    }

    @TextProp
    fun setTitle(title: CharSequence){
        textView_post_title.text = title
    }

    @TextProp
    fun setBody(body: CharSequence){
        textView_post_body.text = body
    }

    @CallbackProp
    fun setClickListener(onClickListener: OnClickListener?){
        imageView_post_insert.setOnClickListener(onClickListener)
    }
}