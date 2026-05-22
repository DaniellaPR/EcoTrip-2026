package com.example.ecotrip2026grupo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ecotrip2026grupo1.ui.theme.EcoTrip2026Grupo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcoTrip2026Grupo1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EcoTripApp()
                }
            }
        }
    }
}

@Composable
fun EcoTripApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FormularioRuta
    ) {
        composable<FormularioRuta> {
            // FormularioScreen(...)
        }

        composable<ResumenRuta> { backStackEntry ->
            val resumenDatos = backStackEntry.toRoute<ResumenRuta>()
            // ResumenScreen(datos = resumenDatos, ...)
        }
    }
}