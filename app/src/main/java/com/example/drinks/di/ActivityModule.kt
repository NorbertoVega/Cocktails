package com.example.drinks.di

import com.example.drinks.data.repository.RepositoryImpl
import com.example.drinks.data.repository.Repository
import com.example.drinks.domain.usecases.GetSaveDrinks
import com.example.drinks.domain.usecases.GetSaveDrinksImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(getSaveDrinks: GetSaveDrinksImpl): GetSaveDrinks

    @Binds
    abstract fun bindDataSourceImpl(repositoryImpl: RepositoryImpl): Repository

}