package com.duoc.monitorsilos.model

data class Alerta(
    val id: Int,
    val siloId: Int,
    val mensaje: String,
    val nivelCritico: EstadoSilo,
    val fecha: String,
    val atendida: Boolean = false
)