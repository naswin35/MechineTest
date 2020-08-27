package com.example.codingtest.di.module



import com.example.codingtest.MainActivity
import com.example.codingtest.ProductDetails
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class  ActivityModule {


    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeProductDetails(): ProductDetails




}