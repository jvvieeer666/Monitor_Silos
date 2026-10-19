package com.duoc.monitorsilos.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.duoc.monitorsilos.data.PreferencesManager
import com.duoc.monitorsilos.model.Rol
import com.duoc.monitorsilos.screens.*

object Rutas {
    const val LOGIN = "login"
    const val OPERARIO = "operario/{usuario}"
    const val SUPERVISOR = "supervisor/{usuario}"
    const val JEFATURA = "jefatura/{usuario}"
    const val GRANJAS = "granjas"
    const val ALERTAS = "alertas"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val cerrarSesion = {
        PreferencesManager(context).cerrarSesion()
        navController.navigate(Rutas.LOGIN) {
            popUpTo(0) { inclusive = true }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Rutas.LOGIN
    ) {
        // Pantalla de Login
        composable(Rutas.LOGIN) {
            PantallaLogin(
                onLoginExitoso = { usuario ->
                    val ruta = when (usuario.rol) {
                        Rol.OPERARIO -> "operario/${usuario.nombre}"
                        Rol.SUPERVISOR -> "supervisor/${usuario.nombre}"
                        Rol.JEFATURA -> "jefatura/${usuario.nombre}"
                    }
                    navController.navigate(ruta) {
                        popUpTo(Rutas.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla Operario
        composable(
            route = Rutas.OPERARIO,
            arguments = listOf(navArgument("usuario") { type = NavType.StringType })
        ) {
            PantallaOperario(
                onVerGranjas = { navController.navigate(Rutas.GRANJAS) },
                onVerAlertas = { navController.navigate(Rutas.ALERTAS) },
                onCerrarSesion = cerrarSesion
            )
        }

        // Pantalla Supervisor
        composable(
            route = Rutas.SUPERVISOR,
            arguments = listOf(navArgument("usuario") { type = NavType.StringType })
        ) {
            PantallaSupervisor(
                onVerGranjas = { navController.navigate(Rutas.GRANJAS) },
                onCerrarSesion = cerrarSesion
            )
        }

        // Pantalla Jefatura
        composable(
            route = Rutas.JEFATURA,
            arguments = listOf(navArgument("usuario") { type = NavType.StringType })
        ) {
            PantallaJefatura(
                onVerGranjas = { navController.navigate(Rutas.GRANJAS) },
                onVerAlertas = { navController.navigate(Rutas.ALERTAS) },
                onCerrarSesion = cerrarSesion
            )
        }

        // Pantalla Granjas
        composable(Rutas.GRANJAS) {
            PantallaGranjas(
                onVolver = { navController.popBackStack() }
            )
        }

        // Pantalla Alertas
        composable(Rutas.ALERTAS) {
            PantallaAlertas(
                onVolver = { navController.popBackStack() }
            )
        }
    }
}
