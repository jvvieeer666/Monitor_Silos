package com.duoc.monitorsilos.model

class Movimiento (
    val id: Int,
    val siloId: Int,
    val tipo: TipoMovimiento,
    val cantidadKg: Double,
    val fecha: String,
    val operario : String
)
enum class TipoMovimiento{
    CARGA,
    DESCARGA,
    CONSUMO
}
