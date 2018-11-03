package com.vlab.experiment.ratlabmvvm.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.vlab.experiment.ratlabmvvm.R

@ModelView(saveViewState = true, autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class InsertCommentItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_insert_comment, this)
    }

}