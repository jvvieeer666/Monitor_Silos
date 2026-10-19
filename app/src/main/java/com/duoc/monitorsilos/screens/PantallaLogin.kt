package com.duoc.monitorsilos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duoc.monitorsilos.ViewModel.LoginViewModel
import com.duoc.monitorsilos.model.Rol
import com.duoc.monitorsilos.model.Usuario


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(
    loginViewModel: LoginViewModel = viewModel(),
    onLoginExitoso: (usuario: Usuario) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("🏢 Monitor de Silos - Acceso") }) }
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
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo Nombre de Usuario
            OutlinedTextField(
                value = loginViewModel.nombreInput,
                onValueChange = { loginViewModel.nombreInput = it },
                label = { Text("Nombre Completo") },
                isError = loginViewModel.errorNombre != null,
                trailingIcon = {
                    if (loginViewModel.errorNombre != null) {
                        Icon(Icons.Default.Warning, contentDescription = "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                supportingText = {
                    loginViewModel.errorNombre?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Selecciona tu Rol Autorizado:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Selector de Rol
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FilterChip(
                    selected = loginViewModel.rolSeleccionado == Rol.OPERARIO,
                    onClick = { loginViewModel.rolSeleccionado = Rol.OPERARIO },
                    label = { Text("👷 Operario") }
                )
                FilterChip(
                    selected = loginViewModel.rolSeleccionado == Rol.SUPERVISOR,
                    onClick = { loginViewModel.rolSeleccionado = Rol.SUPERVISOR },
                    label = { Text("👮 Supervisor") }
                )
                FilterChip(
                    selected = loginViewModel.rolSeleccionado == Rol.JEFATURA,
                    onClick = { loginViewModel.rolSeleccionado = Rol.JEFATURA },
                    label = { Text("📊 Jefatura") }
                )
            }

            // Mensaje de Error General de Autenticación
            loginViewModel.errorLoginGeneral?.let { error ->
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { loginViewModel.autenticar { usuario -> onLoginExitoso(usuario) } },
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Ingresar")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Guía/Sugerencia para pruebas durante la evaluación
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("💡 Usuarios de prueba registrados:", style = MaterialTheme.typography.labelLarge)
                    Text("• Operarios: Juan Pérez, María González", style = MaterialTheme.typography.bodySmall)
                    Text("• Supervisor: Carlos Silva", style = MaterialTheme.typography.bodySmall)
                    Text("• Jefatura: Ana Morales", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}