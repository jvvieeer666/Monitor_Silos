package com.duoc.monitorsilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.duoc.monitorsilos.navegation.AppNavigation
import com.duoc.monitorsilos.screens.PantallaGranjas
import com.duoc.monitorsilos.ui.theme.MonitorSilosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonitorSilosTheme {
                AppNavigation()
            }
        }
    }
}
