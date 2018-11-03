package com.vlab.experiment.ratlabmvvm.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vlab.experiment.ratlabmvvm.R
import com.vlab.experiment.ratlabmvvm.UserActivity

open class BaseFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as UserActivity).setUpNavController(this)
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    fun expandToolbar(expand: Boolean) {
        (activity as UserActivity).expandToolbar(expand)
    }
}