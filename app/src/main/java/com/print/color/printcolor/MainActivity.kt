package com.print.color.printcolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.print.color.printcolor.ui.components.NavigationRail.PcSNavigationRail
import com.print.color.printcolor.ui.theme.PrintColorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrintColorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PcSNavigationRail()
                }
            }
        }
    }
}
