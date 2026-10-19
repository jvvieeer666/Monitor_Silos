package com.duoc.monitorsilos.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.duoc.monitorsilos.components.EstadoConexionBadge
import com.duoc.monitorsilos.data.DatosSimulados
import com.duoc.monitorsilos.model.Alerta
import com.duoc.monitorsilos.model.EstadoSilo
import com.duoc.monitorsilos.utils.NotificationHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaSupervisor(
    onVerGranjas: () -> Unit = {},
    onCerrarSesion: () -> Unit = {}
) {
    val context = LocalContext.current
    var listaAlertas by remember { mutableStateOf(DatosSimulados.obtenerAlertas(context)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("👮 Supervisor - Alertas") },
                actions = {
                    EstadoConexionBadge()
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(onClick = onCerrarSesion) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar Sesión")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onVerGranjas,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("🏠 Ver Granjas")
                }
            }

            Button(
                onClick = {
                    NotificationHelper.enviarNotificacionAlerta(
                        context,
                        "🚨 Alerta de Reposición",
                        "El SILO-A3 requiere reposición urgente de alimento."
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text("Probar Notificación Nativa 🔔")
            }

            Text("Alertas y Estado de Reposición:", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listaAlertas) { alerta ->
                    TarjetaAlertaSupervisor(
                        alerta = alerta,
                        onAtender = { alertaAtendida ->
                            DatosSimulados.marcarAlertaAtendida(context, alertaAtendida.id)
                            listaAlertas = DatosSimulados.obtenerAlertas(context)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TarjetaAlertaSupervisor(alerta: Alerta, onAtender: (Alerta) -> Unit) {
    val color = when (alerta.nivelCritico) {
        EstadoSilo.CRITICO -> Color(0xFFF44336)
        EstadoSilo.ADVERTENCIA -> Color(0xFFFF9800)
        EstadoSilo.NORMAL -> Color(0xFF4CAF50)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = alerta.mensaje, style = MaterialTheme.typography.titleSmall, color = color)
            Text(text = "Fecha: ${alerta.fecha}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(6.dp))

            AnimatedVisibility(
                visible = alerta.atendida,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text("✅ Reposición Coordinada y Guardada", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF2E7D32))
            }

            AnimatedVisibility(
                visible = !alerta.atendida,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Button(
                    onClick = { onAtender(alerta) },
                    colors = ButtonDefaults.buttonColors(containerColor = color)
                ) {
                    Text("Coordinar Reposición")
                }
            }
        }
    }
}
