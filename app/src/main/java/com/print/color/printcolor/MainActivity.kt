package com.print.color.printcolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.print.color.printcolor.ui.components.NavigationRail.PcSNavigationRail
import com.print.color.printcolor.ui.productQuotation.ProductQuotationViewModel
import com.print.color.printcolor.ui.theme.PrintColorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val addProductViewModel: ProductQuotationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PrintColorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PcSNavigationRail(addProductViewModel) {
                        lifecycleScope.launch{
                            addProductViewModel.uiState.collect {
                                it.isValidQuotation()
                            }
                        }
                    }
                }
            }
        }
    }
    /*
    * private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                addProductViewModel.uiState.collect { state ->
                    binding.pbLoading.isVisible = state.isLoading
                    binding.btnAddProduct.isEnabled = state.isValidProduct()
                    showImage(state.imageURL)
                    if (state.error.isNullOrBlank()) {

                    }
                }
            }
        }
    }*/
}


