package com.example.examenej1.model

data class EstadoPermiso(
    val nombre: String = android.Manifest.permission.ACCESS_FINE_LOCATION,
    val concedido: Boolean = false,
    val denegado: Boolean = false,
    val dontAskAgain: Boolean = false,
    val pendientes: MutableList<String> = mutableListOf()
)
