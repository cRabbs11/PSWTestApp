package com.example.pswtestapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pswtestapp.viewModel.MenuViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.pswtestapp.R
import com.example.pswtestapp.utils.OnItemClickListener
import com.example.pswtestapp.view.MainActivity

class MenuFragment: Fragment() {

    private val viewModel: MenuViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val categories by viewModel.categoryLiveData.observeAsState()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(getString(R.string.menu))
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
                    categories?.let {
                        CategoryList(it, innerPadding)
                    }
                }
            }
        }
    }

    @Composable
    fun CategoryList(categories: List<String>?, innerPadding: PaddingValues) {
        LazyColumn(
            Modifier.background(Color.White).padding(innerPadding)
                .fillMaxSize()) {
                //.padding(top = 10.dp, start = 4.dp, end = 4.dp, bottom = 10.dp)) {
            categories?.let { data ->
                itemsIndexed(data) { index, it ->
                    CategoryItem(it, object: OnItemClickListener<String> {
                        override fun onItemClick(t: String) {
                            (activity as MainActivity).openCategoryFragment(t)
                        }
                    })
                    if (index != data.size - 1) {
                        Divider()
                    }
                }
            }
        }
    }


    @Composable
    fun CategoryItem(category: String, clickListener: OnItemClickListener<String>) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(height = 48.dp)
            .clickable { clickListener.onItemClick(category) }
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 14.dp, top = 12.dp, bottom = 12.dp),
                fontSize = 14.sp,
                text = category)
        }
    }
}