package com.duoc.monitorsilos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.duoc.monitorsilos.components.EstadoConexionBadge
import com.duoc.monitorsilos.data.DatosSimulados

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaJefatura(
    onVerGranjas: () -> Unit = {},
    onVerAlertas: () -> Unit = {},
    onCerrarSesion: () -> Unit = {}
) {
    val context = LocalContext.current
    val movimientos = DatosSimulados.obtenerMovimientos(context)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("📊 Jefatura - Historial") },
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
                OutlinedButton(
                    onClick = onVerAlertas,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("🚨 Ver Alertas")
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Resumen General", style = MaterialTheme.typography.titleMedium)
                    Text("Total Silos Monitoreados: ${DatosSimulados.silos.size}")
                    Text("Granjas Activas: ${DatosSimulados.granjas.size}")
                    Text("Total Movimientos Guardados: ${movimientos.size}")
                }
            }

            Text("Historial de Cargas y Consumos:", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movimientos) { mov ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Silo ID: ${mov.siloId} - Tipo: ${mov.tipo}")
                            Text("Cantidad: ${mov.cantidadKg} Kg")
                            Text("Registrado por: ${mov.operario} (${mov.fecha})", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
