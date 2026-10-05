package com.example.tiendadeportiva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tiendadeportiva.models.CarritoDeCompras
import com.example.tiendadeportiva.models.Producto
import com.example.tiendadeportiva.ui.theme.ScreenCarritoCompras
import com.example.tiendadeportiva.ui.theme.ScreenPublicaciones
import com.example.tiendadeportiva.ui.theme.TiendaDeportivaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TiendaDeportivaTheme {
                TiendaDeportivaApp()
            }
        }
    }
}

@Composable
fun TiendaDeportivaApp() {
    // 1. Creación de los dos objetos Producto de ejemplo
    val producto1 = remember {
        Producto(
            idProducto = 1,
            nombre = "Zapatillas Running Ultra Light",
            descripcion = "Zapatillas de alto rendimiento para maratón y entrenamiento diario con máxima amortiguación y ligereza.",
            deporte = "Running",
            marca = "Nike",
            precio = 129.99,
            stock = 15
        )
    }

    val producto2 = remember {
        Producto(
            idProducto = 2,
            nombre = "Pelota de Fútbol Oficial Pro",
            descripcion = "Balón térmicamente sellado con máxima precisión de vuelo y durabilidad extrema.",
            deporte = "Fútbol",
            marca = "Adidas",
            precio = 45.50,
            stock = 20
        )
    }

    val listaPublicaciones = remember { listOf(producto1, producto2) }

    // 2. Carrito inicializado con los dos productos de las publicaciones
    val productosEnCarrito = remember { mutableStateListOf(producto1, producto2) }

    // Estado para controlar la pantalla actual ("publicaciones" o "carrito")
    var pantallaActual by remember { mutableStateOf("publicaciones") }

    val carrito = CarritoDeCompras(
        idCarrito = 1,
        productos = productosEnCarrito,
        montoTotal = productosEnCarrito.sumOf { it.precio }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = pantallaActual == "publicaciones",
                    onClick = { pantallaActual = "publicaciones" },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Publicaciones"
                        )
                    },
                    label = { Text("Publicaciones") }
                )
                NavigationBarItem(
                    selected = pantallaActual == "carrito",
                    onClick = { pantallaActual = "carrito" },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrito"
                        )
                    },
                    label = { Text("Carrito (${productosEnCarrito.size})") }
                )
            }
        }
    ) { innerPadding ->
        Modifier.padding(innerPadding)
        if (pantallaActual == "publicaciones") {
            ScreenPublicaciones(
                productos = listaPublicaciones,
                onAgregarAlCarrito = { producto ->
                    if (!productosEnCarrito.contains(producto)) {
                        productosEnCarrito.add(producto)
                    }
                },
                onIrAlCarrito = {
                    pantallaActual = "carrito"
                }
            )
        } else {
            ScreenCarritoCompras(
                carrito = carrito,
                onVolverAPublicaciones = {
                    pantallaActual = "publicaciones"
                },
                onEliminarProducto = { producto ->
                    productosEnCarrito.remove(producto)
                },
                onVaciarCarrito = {
                    productosEnCarrito.clear()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TiendaDeportivaAppPreview() {
    TiendaDeportivaTheme {
        TiendaDeportivaApp()
    }
}
