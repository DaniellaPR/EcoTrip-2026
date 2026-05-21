package com.example.ecotrip2026grupo1

import kotlinx.serialization.Serializable

/**
 * Representa la pantalla del Formulario de Registro de Viaje.
 * Al ser un objeto, no transporta parámetros iniciales.
 */
@Serializable
object FormularioRuta

/**
 * Representa la pantalla de Resumen de Ruta.
 * Los parámetros declarados aquí definen estrictamente el contrato de datos
 * requerido para construir la UI de destino sin usar Bundles manuales.
 */
@Serializable
data class ResumenRuta(
    val origen: String,
    val destino: String,
    val distanciaKm: Double,
    val huellaCarbonoKg: Double
)

/**
 * Data Class pura para el manejo de preferencias globales persistentes en disco.
 */
data class PreferenciaViaje(
    val ultimoOrigen: String = "",
    val ultimoDestino: String = "",
    val totalViajesCalculados: Int = 0
)