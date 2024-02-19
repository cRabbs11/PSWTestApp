package com.example.pswtestapp.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pswtestapp.utils.Constants

class ViewModelFactory(private val category: String?,
                       private val productId: Int?): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            ProductViewModel::class.java -> {
                if (category!=null) {
                    ProductViewModel(category = category, productId = null)
                } else if(productId!=null) {
                    ProductViewModel(category = null, productId = productId)
                } else {
                    throw IllegalStateException(Constants.EXCEPTION_MESSAGE_ARGUMENT_IS_NULL)
                }

            }
            else -> {
                throw IllegalStateException(Constants.EXCEPTION_MESSAGE_UNKNOWN_VIEW_MODEL)
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory(category: String? = null,
                     productId: Int? = null) =
    ViewModelFactory(category = category,
        productId = productId)