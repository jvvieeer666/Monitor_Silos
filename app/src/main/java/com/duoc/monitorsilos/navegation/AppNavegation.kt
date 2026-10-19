package com.duoc.monitorsilos.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.duoc.monitorsilos.model.Rol
import com.duoc.monitorsilos.screens.*

object Rutas {
    const val LOGIN = "login"
    const val OPERARIO = "operario/{usuario}"
    const val SUPERVISOR = "supervisor/{usuario}"
    const val JEFATURA = "jefatura/{usuario}"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.LOGIN
    ) {
        // Pantalla de Login con autenticación
        composable(Rutas.LOGIN) {
            PantallaLogin(
                onLoginExitoso = { usuario ->
                    val ruta = when (usuario.rol) {
                        Rol.OPERARIO -> "operario/${usuario.nombre}"
                        Rol.SUPERVISOR -> "supervisor/${usuario.nombre}"
                        Rol.JEFATURA -> "jefatura/${usuario.nombre}"
                    }
                    navController.navigate(ruta)
                }
            )
        }

        // Pantalla Operario
        composable(
            route = Rutas.OPERARIO,
            arguments = listOf(navArgument("usuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreUsuario = backStackEntry.arguments?.getString("usuario") ?: "Operario"
            PantallaOperario()
        }

        // Pantalla Supervisor
        composable(
            route = Rutas.SUPERVISOR,
            arguments = listOf(navArgument("usuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreUsuario = backStackEntry.arguments?.getString("usuario") ?: "Supervisor"
            PantallaSupervisor()
        }

        // Pantalla Jefatura
        composable(
            route = Rutas.JEFATURA,
            arguments = listOf(navArgument("usuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreUsuario = backStackEntry.arguments?.getString("usuario") ?: "Jefatura"
            PantallaJefatura()
        }
    }
}