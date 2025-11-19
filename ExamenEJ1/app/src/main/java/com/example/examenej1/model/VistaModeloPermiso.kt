package com.example.examenej1.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VistaModeloPermiso : ViewModel() {
    private val _estado = MutableStateFlow(EstadoPermiso())
    val estado: StateFlow<EstadoPermiso> = _estado

    fun procesar(
        concedido: Boolean,
        denegado: Boolean,
        dontAskAgain: Boolean
    ) {
        _estado.value = _estado.value.copy(
            concedido = concedido,
            denegado = denegado,
            dontAskAgain = dontAskAgain
        )

        if (!concedido && !_estado.value.pendientes.contains(_estado.value.nombre)) {
            _estado.value.pendientes.add(_estado.value.nombre)
        }
    }
}
