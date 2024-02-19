package com.example.pswtestapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.example.pswtestapp.App
import com.example.pswtestapp.R
import com.example.pswtestapp.domain.Interactor
import com.example.pswtestapp.ui.theme.PSWTestAppTheme
import com.example.pswtestapp.utils.Constants.KEY_BUNDLE_CATEGORY
import com.example.pswtestapp.utils.Constants.KEY_BUNDLE_PRODUCT_ID
import com.example.pswtestapp.view.fragments.MainFragment
import com.example.pswtestapp.view.fragments.MenuFragment
import com.example.pswtestapp.view.fragments.ProductCategoryFragment
import com.example.pswtestapp.view.fragments.ProductFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var interactor: Interactor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.dagger.inject(this)
        interactor.fillRoom()
        openMainFragment()
    }

    fun openMainFragment() {
        val fragment = MainFragment()
        launchFragment(fragment)
    }

    fun openMenuFragment() {
        val fragment = MenuFragment()
        launchFragment(fragment)
    }

    fun openCategoryFragment(category: String) {
        val bundle = Bundle()
        bundle.putSerializable(KEY_BUNDLE_CATEGORY, category)
        val fragment = ProductCategoryFragment()
        fragment.arguments = bundle
        launchFragment(fragment)
    }

    fun openProductFragment(id: Int) {
        val bundle = Bundle()
        bundle.putSerializable(KEY_BUNDLE_PRODUCT_ID, id)
        val fragment = ProductFragment()
        fragment.arguments = bundle
        launchFragment(fragment)
    }

    private fun launchFragment(fragment: Fragment, tag: String?=null) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount<=1) {
            super.onBackPressed()
            finish()
        } else {
            super.onBackPressed()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PSWTestAppTheme {
        Greeting("Android")
    }
}

