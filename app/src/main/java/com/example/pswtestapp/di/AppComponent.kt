package com.example.pswtestapp.di

import com.example.pswtestapp.di.modules.DataModule
import com.example.pswtestapp.di.modules.DomainModule
import com.example.pswtestapp.view.MainActivity
import com.example.pswtestapp.viewModel.MenuViewModel
import com.example.pswtestapp.viewModel.ProductViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DataModule::class,
    DomainModule::class])
interface AppComponent {

    fun inject(menuViewModel: MenuViewModel)
    fun inject(productViewModel: ProductViewModel)
    fun inject(mainActivity: MainActivity)
}