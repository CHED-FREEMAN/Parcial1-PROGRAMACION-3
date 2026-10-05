package com.example.tiendadeportiva.models

import java.util.Date

class Pedido (
    val idPedido: Int,
    val fecha: Date,
    var estado: EstadoPedido,
    var montoTotal: Double
) {
    fun confirmarPago(): Boolean {
        return false
    }

    fun actualizarEstado(nuevoEstado: EstadoPedido) {

    }

    fun generarComprobante(): String {
        return ""
    }
}
