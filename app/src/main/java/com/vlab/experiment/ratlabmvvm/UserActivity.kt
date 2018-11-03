package com.vlab.experiment.ratlabmvvm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import com.vlab.experiment.ratlabmvvm.core.setupWithNavController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.user_activity.*
import java.util.concurrent.TimeUnit

class UserActivity : AppCompatActivity() {

    private var disposeSearchView: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity)
    }

    override fun onSupportNavigateUp()
            = findNavController(R.id.userFragment).navigateUp()

    fun observeSearchBar(consumer: Consumer<String>){
        searchView.visibility = View.VISIBLE
        disposeSearchView = searchView.queryTextChanges()
                .map { text -> text.toString().toLowerCase().trim() }
                .debounce(250, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer)
    }

    fun SearchViewVisibility(visibility : Int){
        searchView.visibility = visibility
        disposeSearchView?.dispose()
    }

    override fun onPause() {
        super.onPause()
        disposeSearchView?.dispose()
    }

    fun expandToolbar(expand: Boolean) {
        appBar.setExpanded(expand, expand)
    }

    fun setUpNavController(fragment: Fragment){
        toolbar.setupWithNavController( NavHostFragment.findNavController(fragment))
    }
}
