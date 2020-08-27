package com.example.codingtest.base

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import dagger.android.support.DaggerAppCompatActivity

import com.example.codingtest.api.ApiInterface
import com.example.codingtest.utils.AppRxSchedulers

import javax.inject.Inject


abstract class BaseActivity<VM : ViewModel> : DaggerAppCompatActivity(){

    private lateinit var fullScreenErrorDialog: Dialog

    @Inject
    lateinit var viewModelFactory: dagger.Lazy<ViewModelProvider.Factory>


    @Inject
    lateinit var rxSchedulers: dagger.Lazy<AppRxSchedulers>

    @Inject
    lateinit var api: dagger.Lazy<ApiInterface>

    private lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun provideLayout(): Int

    protected abstract fun provideViewModelClass(): Class<VM>

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayout())

        viewModel = ViewModelProvider(this, viewModelFactory.get())
            .get(provideViewModelClass())
        adjustFontScale(resources.configuration)

    }

    protected fun getViewModel(): VM {
        return viewModel
    }


    open fun adjustFontScale(configuration: Configuration) {
        if (configuration.fontScale > 1.20) {
            configuration.fontScale = 1.20.toFloat()
            val metrics = resources.displayMetrics
            val wm =
                getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density
            baseContext.resources.updateConfiguration(configuration, metrics)
        }
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
        try {

        } catch (e: IllegalArgumentException) {
            Log.e("BASE_ACTIVITY", "Unregister Receiver : ", e)
        }
    }






}