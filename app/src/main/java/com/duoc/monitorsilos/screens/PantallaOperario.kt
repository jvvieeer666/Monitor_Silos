package com.duoc.monitorsilos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duoc.monitorsilos.model.TipoMovimiento
import com.duoc.monitorsilos.viewmodel.OperarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaOperario( viewModel: OperarioViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Operario - Registrar Movimiento") }) }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Text("Registrar Carga / Consumo de Alimento", style = MaterialTheme.typography.titleMedium)
            // ID del Silo
            OutlinedTextField(
                value = viewModel.siloIdInput,
                onValueChange = { viewModel.siloIdInput = it },
                label = { Text("ID del Silo (ej: 1)") },
                isError = viewModel.errorSiloId != null,
                trailingIcon = {
                    if (viewModel.errorSiloId != null) {
                        Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                supportingText = { viewModel.errorSiloId?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // Cantidad Kg
            OutlinedTextField(
                value = viewModel.cantidadInput,
                onValueChange = { viewModel.cantidadInput = it },
                label = { Text("Cantidad en Kg") },
                isError = viewModel.errorCantidad != null,
                trailingIcon = {
                    if (viewModel.errorCantidad != null) {
                        Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                supportingText = { viewModel.errorCantidad?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            // Nombre Operario
            OutlinedTextField(
                value = viewModel.operarioNombreInput,
                onValueChange = { viewModel.operarioNombreInput = it },
                label = { Text("Nombre Operario") },
                isError = viewModel.errorOperario != null,
                supportingText = { viewModel.errorOperario?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
                modifier = Modifier.fillMaxWidth()
            )

            // Tipo de Movimiento (Selector)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Tipo:")
                FilterChip(
                    selected = viewModel.tipoMovimientoSeleccionado == TipoMovimiento.CARGA,
                    onClick = { viewModel.tipoMovimientoSeleccionado = TipoMovimiento.CARGA },
                    label = { Text("Carga") }
                )
                FilterChip(
                    selected = viewModel.tipoMovimientoSeleccionado == TipoMovimiento.CONSUMO,
                    onClick = { viewModel.tipoMovimientoSeleccionado = TipoMovimiento.CONSUMO },
                    label = { Text("Consumo") }
                )
            }

            Button(
                onClick = { viewModel.validarYRegistrar() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Movimiento")
            }

            viewModel.mensajeExito?.let {
                Text(it, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}