package com.duoc.monitorsilos.model

data class Silo(
    val id: Int,
    val granjaId: Int,
    val identificacion: String,
    val capacidadTotalKg: Double,
    val nivelActualKg: Double,
    val fechaActualizacion: String,
    val estado: EstadoSilo
) {
    val porcentajeDisponible: Double
        get() = if (capacidadTotalKg > 0) (nivelActualKg / capacidadTotalKg) * 100 else 0.0
}

enum class EstadoSilo {
    NORMAL,
    ADVERTENCIA,
    CRITICO
}