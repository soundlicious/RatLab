package com.vlab.experiment.ratlabmvvm.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.vlab.experiment.ratlabmvvm.R
import kotlinx.android.synthetic.main.item_user.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class UserItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        FrameLayout.inflate(context, R.layout.item_user, this).setPadding(8,8,8,8)
    }

    @TextProp
    fun setName(userName:CharSequence){
        textView_userName.text = userName
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        cardView_user.setOnClickListener(clickListener)
    }
}