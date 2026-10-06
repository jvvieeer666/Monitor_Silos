package com.duoc.monitorsilos.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.duoc.monitorsilos.data.DatosSimulados
import com.duoc.monitorsilos.model.Movimiento
import com.duoc.monitorsilos.model.TipoMovimiento

class OperarioViewModel : ViewModel() {
    var siloIdInput by mutableStateOf("")
    var cantidadInput by mutableStateOf("")
    var tipoMovimientoSeleccionado by mutableStateOf(TipoMovimiento.CARGA)
    var operarioNombreInput by mutableStateOf("")
    var errorSiloId by mutableStateOf<String?>(null)
    var errorCantidad by mutableStateOf<String?>(null)
    var errorOperario by mutableStateOf<String?>(null)
    var mensajeExito by mutableStateOf<String?>(null)

    fun validarYRegistrar(): Boolean {
        var esValido = true
        mensajeExito = null


        val idParsed = siloIdInput.toIntOrNull()
        if (idParsed == null || DatosSimulados.obtenerSiloPorId(idParsed) == null) {
            errorSiloId = "Silo no encontrado (Ej: 1 al 6)"
            esValido = false
        } else {
            errorSiloId = null
        }


        val cantidadParsed = cantidadInput.toDoubleOrNull()
        if (cantidadParsed == null || cantidadParsed <= 0) {
            errorCantidad = "Ingrese una cantidad válida mayor a 0 kg"
            esValido = false
        } else {
            errorCantidad = null
        }

        if (operarioNombreInput.trim().isEmpty()) {
            errorOperario = "El nombre del operario es requerido"
            esValido = false
        } else {
            errorOperario = null
        }

        if (esValido) {

            val nuevoMov = Movimiento(
                id = (DatosSimulados.movimientos.size + 1),
                siloId = idParsed!!,
                tipo = tipoMovimientoSeleccionado,
                cantidadKg = cantidadParsed!!,
                fecha = "2026-10-05 17:30",
                operario = operarioNombreInput
            )

            siloIdInput = ""
            cantidadInput = ""
            operarioNombreInput = ""
            mensajeExito = "¡Movimiento registrado con éxito!"
        }

        return esValido
    }
}