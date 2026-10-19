package com.duoc.monitorsilos.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.duoc.monitorsilos.data.DatosSimulados
import com.duoc.monitorsilos.model.Rol
import com.duoc.monitorsilos.model.Usuario

class LoginViewModel : ViewModel() {
    var nombreInput by mutableStateOf("")
    var rolSeleccionado by mutableStateOf(Rol.OPERARIO)

    // Estados de Error Visual
    var errorNombre by mutableStateOf<String?>(null)
    var errorLoginGeneral by mutableStateOf<String?>(null)

    fun autenticar(onExito: (Usuario) -> Unit) {
        // Resetear errores
        errorNombre = null
        errorLoginGeneral = null

        val nombreLimpio = nombreInput.trim()

        // 1. Validar campo vacío
        if (nombreLimpio.isEmpty()) {
            errorNombre = "El nombre de usuario no puede estar vacío"
            return
        }

        // 2. Validar credenciales contra los datos simulados
        val usuarioEncontrado = DatosSimulados.validarUsuario(nombreLimpio, rolSeleccionado)

        if (usuarioEncontrado != null) {
            onExito(usuarioEncontrado)
        } else {
            errorLoginGeneral = "Usuario no registrado o no posee el rol de ${rolSeleccionado.name.lowercase().replaceFirstChar { it.uppercase() }}."
        }
    }
}