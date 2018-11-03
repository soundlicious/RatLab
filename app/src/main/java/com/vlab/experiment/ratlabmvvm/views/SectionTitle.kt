package com.vlab.experiment.ratlabmvvm.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.vlab.experiment.ratlabmvvm.R
import kotlinx.android.synthetic.main.item_section_title.view.*

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class SectionTitle @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_section_title, this)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        textView_title.text = title
        orientation = VERTICAL
    }

    @TextProp
    fun setSubtitle(subtitle: CharSequence?) {
        textView_subtitle.visibility = if(subtitle.isNullOrBlank()) View.GONE else View.VISIBLE
        textView_subtitle.text = subtitle
    }
}