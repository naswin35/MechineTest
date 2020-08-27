package com.example.codingtest.di.component


import com.example.codingtest.CommonApp
import com.example.codingtest.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            ContextModule::class,
            ActivityModule::class,
            ApiModule::class,
            AppModule::class,
            AndroidSupportInjectionModule::class,
            ViewModelModule::class

        ]
)
interface AppComponent : AndroidInjector<CommonApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: CommonApp): Builder

        fun build(): AppComponent
    }
}