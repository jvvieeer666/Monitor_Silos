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
        // Pantalla de Login
        composable(Rutas.LOGIN) {
            PantallaLogin(
                onLoginExitoso = { usuario, rol ->
                    val ruta = when (rol) {
                        Rol.OPERARIO -> "operario/$usuario"
                        Rol.SUPERVISOR -> "supervisor/$usuario"
                        Rol.JEFATURA -> "jefatura/$usuario"
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
            val usuario = backStackEntry.arguments?.getString("usuario") ?: "Usuario"
            PantallaOperario() // Puedes pasar 'usuario' a tu ViewModel o pantalla
        }

        // Pantalla Supervisor
        composable(
            route = Rutas.SUPERVISOR,
            arguments = listOf(navArgument("usuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val usuario = backStackEntry.arguments?.getString("usuario") ?: "Supervisor"
            PantallaSupervisor()
        }

        // Pantalla Jefatura
        composable(
            route = Rutas.JEFATURA,
            arguments = listOf(navArgument("usuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val usuario = backStackEntry.arguments?.getString("usuario") ?: "Jefatura"
            PantallaJefatura()
        }
    }
}