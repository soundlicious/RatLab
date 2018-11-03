package com.vlab.experiment.ratlabmvvm.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.vlab.experiment.ratlabmvvm.R
import kotlinx.android.synthetic.main.item_comment.view.*

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CommentItem @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        View.inflate(context, R.layout.item_comment, this).setPadding(8,8,8,8)
    }

    @TextProp
    fun setUsername(userName: CharSequence){
        textView_comment_userName.text = userName
    }

    @TextProp
    fun setMail(mail: CharSequence){
        textView_comment_mail.text = mail
    }

    @TextProp
    fun setBody(body: CharSequence){
        textView_comment_body.text = body
    }
}