package com.example.codingtest.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codingtest.StudyViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

import com.example.codingtest.di.factory.ViewModelFactory
import com.example.codingtest.di.scope.ViewModelKey


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    // Settings


    @Binds
    @IntoMap
    @ViewModelKey(StudyViewModel::class)
    internal abstract fun bindStudyViewModel(studyViewModel: StudyViewModel): ViewModel




}