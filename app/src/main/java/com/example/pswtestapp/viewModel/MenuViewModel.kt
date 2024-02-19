package com.example.pswtestapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pswtestapp.App
import com.example.pswtestapp.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel: ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    val categoryLiveData = MutableLiveData<List<String>>()

    init {
        App.instance.dagger.inject(this)
        viewModelScope.launch(Dispatchers.IO) {
            val list = interactor.getCategories()
            categoryLiveData.postValue(list)
        }
    }
}