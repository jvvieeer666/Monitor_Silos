package com.duoc.monitorsilos.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duoc.monitorsilos.components.TarjetaGranja
import com.duoc.monitorsilos.data.DatosSimulados

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaGranjas() {
    val granjas = DatosSimulados.obtenerGranjas()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Monitor de Silos") })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(granjas) { granja ->
                TarjetaGranja(granja)
            }
        }
    }
}