package com.example.pswtestapp.di.modules

import com.example.pswtestapp.data.dao.ProductDao
import com.example.pswtestapp.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideInteractor(productDao: ProductDao): Interactor = Interactor(productDao)
}