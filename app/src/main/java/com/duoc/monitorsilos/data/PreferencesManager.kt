package com.duoc.monitorsilos.data

import android.content.Context
import android.content.SharedPreferences
import com.duoc.monitorsilos.model.Movimiento
import com.duoc.monitorsilos.model.Rol
import com.duoc.monitorsilos.model.TipoMovimiento
import com.duoc.monitorsilos.model.Usuario
import org.json.JSONArray
import org.json.JSONObject

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("silos_preferences", Context.MODE_PRIVATE)

    // --- Persistencia de Sesión ---
    fun guardarSesion(usuario: Usuario) {
        prefs.edit()
            .putInt("user_id", usuario.id)
            .putString("user_nombre", usuario.nombre)
            .putString("user_rol", usuario.rol.name)
            .apply()
    }

    fun obtenerSesion(): Usuario? {
        val id = prefs.getInt("user_id", -1)
        val nombre = prefs.getString("user_nombre", null)
        val rolStr = prefs.getString("user_rol", null)
        if (id != -1 && nombre != null && rolStr != null) {
            return try {
                Usuario(id, nombre, Rol.valueOf(rolStr))
            } catch (e: Exception) {
                null
            }
        }
        return null
    }

    fun cerrarSesion() {
        prefs.edit()
            .remove("user_id")
            .remove("user_nombre")
            .remove("user_rol")
            .apply()
    }

    // --- Persistencia de Movimientos ---
    fun guardarMovimientos(movimientos: List<Movimiento>) {
        val jsonArray = JSONArray()
        movimientos.forEach { mov ->
            val obj = JSONObject().apply {
                put("id", mov.id)
                put("siloId", mov.siloId)
                put("tipo", mov.tipo.name)
                put("cantidadKg", mov.cantidadKg)
                put("fecha", mov.fecha)
                put("operario", mov.operario)
            }
            jsonArray.put(obj)
        }
        prefs.edit().putString("movimientos_json", jsonArray.toString()).apply()
    }

    fun obtenerMovimientos(): List<Movimiento>? {
        val jsonStr = prefs.getString("movimientos_json", null) ?: return null
        return try {
            val jsonArray = JSONArray(jsonStr)
            val list = mutableListOf<Movimiento>()
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                list.add(
                    Movimiento(
                        id = obj.getInt("id"),
                        siloId = obj.getInt("siloId"),
                        tipo = TipoMovimiento.valueOf(obj.getString("tipo")),
                        cantidadKg = obj.getDouble("cantidadKg"),
                        fecha = obj.getString("fecha"),
                        operario = obj.getString("operario")
                    )
                )
            }
            list
        } catch (e: Exception) {
            null
        }
    }

    // --- Persistencia de Alertas Atendidas ---
    fun guardarAlertaAtendida(alertaId: Int) {
        val set = prefs.getStringSet("alertas_atendidas", emptySet())?.toMutableSet() ?: mutableSetOf()
        set.add(alertaId.toString())
        prefs.edit().putStringSet("alertas_atendidas", set).apply()
    }

    fun obtenerAlertasAtendidas(): Set<Int> {
        val set = prefs.getStringSet("alertas_atendidas", emptySet()) ?: emptySet()
        return set.mapNotNull { it.toIntOrNull() }.toSet()
    }
}
