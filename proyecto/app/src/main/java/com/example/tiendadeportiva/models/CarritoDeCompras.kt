package com.example.tiendadeportiva.models

class CarritoDeCompras (
    val idCarrito: Int,
    var productos: List<Producto>,
    var montoTotal: Double
) {
    fun agregarProducto(producto: Producto, cantidad: Int) {

    }

    fun eliminarProducto(producto: Producto) {

    }

    fun calcularTotal(): Double {
        return 0.0
    }

    fun vaciarCarrito() {

    }
}
