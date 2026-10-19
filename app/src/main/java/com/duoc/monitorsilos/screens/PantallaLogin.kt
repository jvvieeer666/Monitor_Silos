package com.duoc.monitorsilos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duoc.monitorsilos.model.Rol

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(onLoginExitoso: (nombreUsuario: String, rol: Rol) -> Unit) {
    var nombreInput by remember { mutableStateOf("") }
    var rolSeleccionado by remember { mutableStateOf(Rol.OPERARIO) }
    var errorNombre by remember { mutableStateOf<String?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("🏢 Monitor de Silos - Inicio de Sesión") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ingresa tus datos para continuar",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo para el Nombre del Usuario
            OutlinedTextField(
                value = nombreInput,
                onValueChange = {
                    nombreInput = it
                    if (it.trim().isNotEmpty()) errorNombre = null
                },
                label = { Text("Nombre de usuario / Operario") },
                isError = errorNombre != null,
                supportingText = {
                    errorNombre?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Selecciona tu Rol:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Selector de Roles (FilterChips)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FilterChip(
                    selected = rolSeleccionado == Rol.OPERARIO,
                    onClick = { rolSeleccionado = Rol.OPERARIO },
                    label = { Text("👷 Operario") }
                )
                FilterChip(
                    selected = rolSeleccionado == Rol.SUPERVISOR,
                    onClick = { rolSeleccionado = Rol.SUPERVISOR },
                    label = { Text("👮 Supervisor") }
                )
                FilterChip(
                    selected = rolSeleccionado == Rol.JEFATURA,
                    onClick = { rolSeleccionado = Rol.JEFATURA },
                    label = { Text("📊 Jefatura") }
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Botón de Ingreso
            Button(
                onClick = {
                    if (nombreInput.trim().isEmpty()) {
                        errorNombre = "Por favor, ingresa tu nombre"
                    } else {
                        onLoginExitoso(nombreInput.trim(), rolSeleccionado)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Ingresar a la Aplicación")
            }
        }
    }
}