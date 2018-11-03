package com.vlab.experiment.ratlabmvvm.core

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

//fun Fragment.navigateTo(@IdRes actionId: Int, arg: Parcelable? = null) =  {
//    val bundle = arg?.let {
//        Bundle().apply {  }
//    }
//    findNavController().navigate(actionId, bundle)
//}
//
//fun Fragment.navigateTo(@IdRes actionId: Int, arg: Serializable? = null)   {
//    val bundle = arg?.let {
//        Bundle().apply {  }
//    }
//    findNavController().navigate(actionId, bundle)
//}
//
//fun Fragment.navigateTo(@IdRes actionId: Int, arg: Bundle?) =  {
//    findNavController().navigate(actionId, arg)
//}

fun <T:NavDirections>Fragment.navigateTo(direction: T) {
    findNavController().navigate(direction)
}
