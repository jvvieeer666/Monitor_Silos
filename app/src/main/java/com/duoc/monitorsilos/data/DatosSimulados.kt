package com.duoc.monitorsilos.data

import com.duoc.monitorsilos.model.Alerta
import com.duoc.monitorsilos.model.EstadoSilo
import com.duoc.monitorsilos.model.Granja
import com.duoc.monitorsilos.model.Movimiento
import com.duoc.monitorsilos.model.Silo
import com.duoc.monitorsilos.model.TipoMovimiento

object DatosSimulados {

    // ==================== SILOS ====================
    val silos: List<Silo> = listOf(
        Silo(
            id = 1, granjaId = 1, identificacion = "SILO-A1",
            capacidadTotalKg = 10000.0, nivelActualKg = 8500.0,
            fechaActualizacion = "2026-09-25 20:30", estado = EstadoSilo.NORMAL
        ),
        Silo(
            id = 2, granjaId = 1, identificacion = "SILO-A2",
            capacidadTotalKg = 10000.0, nivelActualKg = 3200.0,
            fechaActualizacion = "2026-09-25 20:30", estado = EstadoSilo.ADVERTENCIA
        ),
        Silo(
            id = 3, granjaId = 1, identificacion = "SILO-A3",
            capacidadTotalKg = 8000.0, nivelActualKg = 800.0,
            fechaActualizacion = "2026-09-25 20:30", estado = EstadoSilo.CRITICO
        ),
        Silo(
            id = 4, granjaId = 2, identificacion = "SILO-B1",
            capacidadTotalKg = 12000.0, nivelActualKg = 11000.0,
            fechaActualizacion = "2026-09-25 19:45", estado = EstadoSilo.NORMAL
        ),
        Silo(
            id = 5, granjaId = 2, identificacion = "SILO-B2",
            capacidadTotalKg = 12000.0, nivelActualKg = 1500.0,
            fechaActualizacion = "2026-09-25 19:45", estado = EstadoSilo.CRITICO
        ),
        Silo(
            id = 6, granjaId = 3, identificacion = "SILO-C1",
            capacidadTotalKg = 9000.0, nivelActualKg = 7200.0,
            fechaActualizacion = "2026-09-25 21:00", estado = EstadoSilo.NORMAL
        )
    )

    // ==================== GRANJAS ====================
    val granjas: List<Granja> = listOf(
        Granja(
            id = 1, nombre = "Granja Melipilla", ubicacion = "Melipilla, RM",
            silos = silos.filter { it.granjaId == 1 }
        ),
        Granja(
            id = 2, nombre = "Granja San Antonio", ubicacion = "San Antonio, V Región",
            silos = silos.filter { it.granjaId == 2 }
        ),
        Granja(
            id = 3, nombre = "Granja Talca", ubicacion = "Talca, VII Región",
            silos = silos.filter { it.granjaId == 3 }
        )
    )

    // ==================== ALERTAS ====================
    val alertas: List<Alerta> = listOf(
        Alerta(
            id = 1, siloId = 3, mensaje = "SILO-A3 en nivel crítico (10%)",
            nivelCritico = EstadoSilo.CRITICO, fecha = "2026-09-25 20:30"
        ),
        Alerta(
            id = 2, siloId = 5, mensaje = "SILO-B2 en nivel crítico (12.5%)",
            nivelCritico = EstadoSilo.CRITICO, fecha = "2026-09-25 19:45"
        ),
        Alerta(
            id = 3, siloId = 2, mensaje = "SILO-A2 en nivel de advertencia (32%)",
            nivelCritico = EstadoSilo.ADVERTENCIA, fecha = "2026-09-25 20:30"
        )
    )

    // ==================== MOVIMIENTOS ====================
    val movimientos: List<Movimiento> = listOf(
        Movimiento(1, 1, TipoMovimiento.CARGA, 5000.0, "2026-09-24 08:00", "Juan Pérez"),
        Movimiento(2, 1, TipoMovimiento.CONSUMO, 500.0, "2026-09-25 12:00", "Sistema"),
        Movimiento(3, 3, TipoMovimiento.CONSUMO, 800.0, "2026-09-25 18:00", "Sistema"),
        Movimiento(4, 5, TipoMovimiento.DESCARGA, 3000.0, "2026-09-23 10:00", "María González")
    )

    // ==================== FUNCIONES DE CONSULTA ====================

    fun obtenerGranjas(): List<Granja> = granjas

    fun obtenerSilosDeGranja(granjaId: Int): List<Silo> =
        silos.filter { it.granjaId == granjaId }

    fun obtenerAlertas(): List<Alerta> = alertas

    fun obtenerMovimientosDeSilo(siloId: Int): List<Movimiento> =
        movimientos.filter { it.siloId == siloId }

    fun obtenerSiloPorId(id: Int): Silo? = silos.find { it.id == id }
}