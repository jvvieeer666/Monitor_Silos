package com.duoc.monitorsilos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duoc.monitorsilos.utils.hayConexionInternet

@Composable
fun EstadoConexionBadge() {
    val context = LocalContext.current
    var estaConectado by remember { mutableStateOf(hayConexionInternet(context)) }

    LaunchedEffect(Unit) {
        estaConectado = hayConexionInternet(context)
    }

    val colorFondo = if (estaConectado) Color(0xFF2E7D32) else Color(0xFFC62828)
    val texto = if (estaConectado) "🟢 Online" else "🔴 Offline"

    Box(
        modifier = Modifier
            .background(colorFondo, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = Color.White,
            fontSize = 11.sp,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
