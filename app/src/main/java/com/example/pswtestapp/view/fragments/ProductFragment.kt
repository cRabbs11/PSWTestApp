package com.example.pswtestapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.rememberAsyncImagePainter
import com.example.pswtestapp.data.entity.Product
import com.example.pswtestapp.utils.Constants
import com.example.pswtestapp.viewModel.ProductViewModel
import com.example.pswtestapp.viewModel.factory

class ProductFragment: Fragment() {

    private val viewModel: ProductViewModel by viewModels{factory(productId = arguments?.getInt(Constants.KEY_BUNDLE_PRODUCT_ID))}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val product by viewModel.productLiveData.observeAsState()
                product?.let {
                    Log.d("BMTH", "productName= ${it.name}")
                    TopAppBar(it)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(product: Product) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(product.name)
                    },
                    navigationIcon = {
                        IconButton(onClick = { activity?.onBackPressed() })
                        {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },)
            },
        ) { innerPadding ->
            ProductItem(product, innerPadding)
        }
    }

    @Composable
    fun ProductItem(product: Product, innerPadding: PaddingValues) {
        Column(modifier = Modifier.padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                modifier = Modifier.size(128.dp))
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 14.dp, top = 12.dp, bottom = 12.dp),
                fontSize = 14.sp,
                text = product.description)

        }
    }
}