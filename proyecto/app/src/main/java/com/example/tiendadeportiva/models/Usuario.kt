package com.example.tiendadeportiva.models

class Usuario (
    val idUsuario: Int,
    val nombre: String,
    var email: String,
    var contraseña: String,
    var direccionEnvio: String
) {
    fun iniciarSesion(): Boolean {
        return true
    }
    fun cerrarSesion(): Unit {}
    fun actualizarPerfil(): Unit {}
}