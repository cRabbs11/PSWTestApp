package com.example.pswtestapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.pswtestapp.data.entity.Product
import com.example.pswtestapp.utils.Constants.KEY_BUNDLE_CATEGORY
import com.example.pswtestapp.utils.OnItemClickListener
import com.example.pswtestapp.view.MainActivity
import com.example.pswtestapp.viewModel.ProductViewModel
import com.example.pswtestapp.viewModel.factory

class ProductCategoryFragment: Fragment() {

    private val viewModel: ProductViewModel by viewModels{factory(category = arguments?.getString(KEY_BUNDLE_CATEGORY))}

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val products by viewModel.productsLiveData.observeAsState()
                Scaffold(
                    topBar = {
                        androidx.compose.material3.TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                products?.let{
                                    if (it.isNotEmpty()) {
                                        Text(it[0].category)
                                    }
                                }
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
                    products?.let {
                        ProductList(it, innerPadding)
                    }
                }
            }
        }
    }

    @Composable
    fun ProductList(products: List<Product>, innerPadding: PaddingValues) {
        LazyColumn(
            Modifier.background(Color.White)
                .fillMaxSize()
                .padding(innerPadding)) {
                itemsIndexed(products) { index, it ->
                    ProductItem(it, object: OnItemClickListener<Int> {
                        override fun onItemClick(t: Int) {
                            (activity as MainActivity).openProductFragment(t)
                        }
                    })
                    if (index != products.size - 1) {
                        Divider()
                    }
                }
        }
    }

    @Composable
    fun ProductItem(product: Product, clickListener: OnItemClickListener<Int>) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .clickable { clickListener.onItemClick(product.id) }
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 14.dp, top = 12.dp, bottom = 12.dp),
                fontSize = 14.sp,
                text = product.name)
        }
    }
}