package com.duoc.monitorsilos.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.duoc.monitorsilos.data.DatosSimulados
import com.duoc.monitorsilos.model.Alerta
import com.duoc.monitorsilos.model.EstadoSilo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaAlertas() {
    val alertas = DatosSimulados.obtenerAlertas()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("🚨 Alertas Activas") })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(alertas) { alerta ->
                TarjetaAlerta(alerta)
            }
        }
    }
}

@Composable
fun TarjetaAlerta(alerta: Alerta) {
    val color = when (alerta.nivelCritico) {
        EstadoSilo.CRITICO -> Color(0xFFF44336)
        EstadoSilo.ADVERTENCIA -> Color(0xFFFF9800)
        EstadoSilo.NORMAL -> Color(0xFF4CAF50)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.15f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = alerta.mensaje,
                style = MaterialTheme.typography.titleMedium,
                color = color
            )
            Text(
                text = "Nivel: ${alerta.nivelCritico.name}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Fecha: ${alerta.fecha}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = if (alerta.atendida) "✅ Atendida" else "⏳ Pendiente",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}