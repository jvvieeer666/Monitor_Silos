package com.duoc.monitorsilos.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.monitorsilos.model.Rol
import com.duoc.monitorsilos.screens.*

object Rutas {
    const val LOGIN = "login"
    const val OPERARIO = "operario"
    const val SUPERVISOR = "supervisor"
    const val JEFATURA = "jefatura"
    const val GRANJAS = "granjas"
    const val ALERTAS = "alertas"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.LOGIN
    ) {
        // Pantalla de selección de rol
        composable(Rutas.LOGIN) {
            PantallaLogin(
                onRolSeleccionado = { rol ->
                    val ruta = when (rol) {
                        Rol.OPERARIO -> Rutas.OPERARIO
                        Rol.SUPERVISOR -> Rutas.SUPERVISOR
                        Rol.JEFATURA -> Rutas.JEFATURA
                    }
                    navController.navigate(ruta)
                }
            )
        }

        // Pantallas por rol
        composable(Rutas.OPERARIO) {
            PantallaOperario()
        }

        composable(Rutas.SUPERVISOR) {
            PantallaSupervisor()
        }

        composable(Rutas.JEFATURA) {
            PantallaJefatura()
        }

        // Pantallas generales (podrían reutilizarse)
        composable(Rutas.GRANJAS) {
            PantallaGranjas()
        }

        composable(Rutas.ALERTAS) {
            PantallaAlertas()
        }
    }
}