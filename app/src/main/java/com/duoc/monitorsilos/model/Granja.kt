package com.duoc.monitorsilos.model

data class Granja(
    val id: Int,
    val nombre: String,
    val ubicacion: String,
    val silos: List<Silo> = emptyList()
)