package com.duoc.monitorsilos.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { startAnimation = true }

    val porcentajeTarget = (silo.porcentajeDisponible / 100f).toFloat()
    val progressAnimated by animateFloatAsState(
        targetValue = if (startAnimation) porcentajeTarget else 0f,
        animationSpec = tween(durationMillis = 1200),
        label = "ProgresoSilo"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        colors = CardDefaults.cardColors(containerColor = colorEstado.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = silo.identificacion, style = MaterialTheme.typography.titleMedium)
            Text(text = "Nivel: ${silo.nivelActualKg.toInt()} kg / ${silo.capacidadTotalKg.toInt()} kg")

            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { progressAnimated },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = colorEstado,
                trackColor = Color.LightGray
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Disponible: ${"%.1f".format(silo.porcentajeDisponible)}%")
            Text(text = "Estado: ${silo.estado.name}", color = colorEstado)
        }
    }
}