package com.duoc.monitorsilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.duoc.monitorsilos.model.EstadoSilo
import com.duoc.monitorsilos.model.Granja
import com.duoc.monitorsilos.model.Silo
import com.duoc.monitorsilos.ui.theme.MonitorSilosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonitorSilosTheme {
                PantallaGranjas()
            }
        }
    }
}

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

@Composable
fun TarjetaGranja(granja: Granja) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Nombre de la granja
            Text(
                text = "🏠 ${granja.nombre}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "📍 ${granja.ubicacion}",
                style = MaterialTheme.typography.bodySmall
            )

            // Lista de silos dentro de la granja
            granja.silos.forEach { silo ->
                TarjetaSilo(silo)
            }
        }
    }
}

@Composable
fun TarjetaSilo(silo: Silo) {
    val colorEstado = when (silo.estado) {
        EstadoSilo.NORMAL -> Color(0xFF4CAF50)      // Verde
        EstadoSilo.ADVERTENCIA -> Color(0xFFFF9800) // Naranja
        EstadoSilo.CRITICO -> Color(0xFFF44336)     // Rojo
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