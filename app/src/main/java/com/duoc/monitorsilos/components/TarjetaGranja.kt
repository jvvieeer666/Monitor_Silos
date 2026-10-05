package com.duoc.monitorsilos.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duoc.monitorsilos.components.TarjetaSilo
import com.duoc.monitorsilos.model.Granja

@Composable
fun TarjetaGranja(granja: Granja) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "🏠 ${granja.nombre}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "📍 ${granja.ubicacion}",
                style = MaterialTheme.typography.bodySmall
            )
            granja.silos.forEach { silo ->
                TarjetaSilo(silo)
            }
        }
    }
}