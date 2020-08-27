package com.example.codingtest.di.module



import com.example.codingtest.StudyRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

import com.example.codingtest.api.ApiInterface
import com.example.codingtest.utils.AppRxSchedulers
import com.example.codingtest.utils.ErrorHandler


import javax.inject.Singleton

@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    @Singleton
    fun provideErrorHandler(retrofit: Retrofit) = ErrorHandler(retrofit)

    @Singleton
    @Provides
    fun provideStudyRepo(
        api: ApiInterface,
        rxSchedulers: AppRxSchedulers
    ): StudyRepository = StudyRepository(
        api = api,
        rxSchedulers = rxSchedulers
    )

}