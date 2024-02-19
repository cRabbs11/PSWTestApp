package com.example.pswtestapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pswtestapp.App
import com.example.pswtestapp.data.entity.Product
import com.example.pswtestapp.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel(private val category: String?, private val productId: Int?): ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    val productsLiveData = MutableLiveData<List<Product>>()
    val productLiveData = MutableLiveData<Product>()

    init {
        App.instance.dagger.inject(this)

        if (!category.isNullOrEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                productsLiveData.postValue(interactor.getProducts(category))
            }
        }

        if (productId!=null) {
            Log.d("BMTH", "productId!=null")
            viewModelScope.launch(Dispatchers.IO) {
                Log.d("BMTH", "productId= ${productId}")
                val product = interactor.getProduct(productId)
                Log.d("BMTH", "productId= ${product?.name}")
                product?.let {
                    productLiveData.postValue(it)
                }
            }
        }
    }
}