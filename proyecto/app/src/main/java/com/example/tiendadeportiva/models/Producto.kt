package com.example.tiendadeportiva.models

class Producto (
    val idProducto: Int,
    val nombre: String,
    var descripcion: String,
    val deporte: String,
    val marca: String,
    var precio: Double,
    var stock: Int
) {
    fun obtenerDetalles(): String {
        return ""
    }

    fun verificarDisponibilidad(cantidad: Int): Boolean {
        return false
    }

    fun actualizarStock(cantidad: Int) {

    }
}
