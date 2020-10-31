package com.example.drinks.di

import com.example.drinks.data.DataSourceImpl
import com.example.drinks.domain.DataSourceRepo
import com.example.drinks.domain.Repo
import com.example.drinks.domain.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: RepoImpl): Repo

    @Binds
    abstract fun bindDataSourceImpl(datasourceImpl: DataSourceImpl): DataSourceRepo

}