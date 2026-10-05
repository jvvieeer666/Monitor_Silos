package com.duoc.monitorsilos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duoc.monitorsilos.model.Rol

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(onRolSeleccionado: (Rol) -> Unit) {
    val roles = Rol.entries

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Selecciona tu rol") }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(roles) { rol ->
                Button(
                    onClick = { onRolSeleccionado(rol) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = when (rol) {
                            Rol.OPERARIO -> "👷 Operario"
                            Rol.SUPERVISOR -> "👔 Supervisor"
                            Rol.JEFATURA -> "📊 Jefatura"
                        }
                    )
                }
            }
        }
    }
}