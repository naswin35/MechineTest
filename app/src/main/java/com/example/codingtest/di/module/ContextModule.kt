package com.example.codingtest.di.module

import android.content.Context
import com.example.codingtest.CommonApp

import dagger.Module
import dagger.Provides


@Module
class ContextModule {

    @Provides
    fun provideContext(application: CommonApp): Context {
        return application.applicationContext
    }
}