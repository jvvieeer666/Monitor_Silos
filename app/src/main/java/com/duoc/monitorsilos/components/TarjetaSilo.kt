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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.duoc.monitorsilos.model.EstadoSilo
import com.duoc.monitorsilos.model.Silo

@Composable
fun TarjetaSilo(silo: Silo) {
    val colorEstado = when (silo.estado) {
        EstadoSilo.NORMAL -> Color(0xFF4CAF50)
        EstadoSilo.ADVERTENCIA -> Color(0xFFFF9800)
        EstadoSilo.CRITICO -> Color(0xFFF44336)
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorEstado.copy(alpha = 0.15f)
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = silo.identificacion,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Nivel: ${silo.nivelActualKg.toInt()} kg / ${silo.capacidadTotalKg.toInt()} kg"
            )
            Text(
                text = "Disponible: ${"%.1f".format(silo.porcentajeDisponible)}%"
            )
            Text(
                text = "Estado: ${silo.estado.name}",
                color = colorEstado,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Actualizado: ${silo.fechaActualizacion}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}