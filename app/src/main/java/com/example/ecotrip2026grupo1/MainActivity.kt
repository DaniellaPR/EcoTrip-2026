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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ecotrip2026grupo1.data.PreferenciasRepositorio
import com.example.ecotrip2026grupo1.data.dataStore
import com.example.ecotrip2026grupo1.ui.model.EcoTripViewModel
import com.example.ecotrip2026grupo1.ui.FormularioScreen
import com.example.ecotrip2026grupo1.ui.ResumenScreen
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
    val contexto = LocalContext.current

    // Instanciamos el Repositorio y el ViewModel utilizando el Factory que creamos
    val repositorio = PreferenciasRepositorio(contexto.dataStore)
    val viewModel: EcoTripViewModel = viewModel(
        factory = EcoTripViewModel.provideFactory(repositorio)
    )

    NavHost(
        navController = navController,
        startDestination = FormularioRuta
    ) {
        composable<FormularioRuta> {
            FormularioScreen(
                viewModel = viewModel,
                onNavegarResumen = { resumenDatos ->
                    // Navegación tipada hacia el resumen
                    navController.navigate(resumenDatos)
                }
            )
        }

        composable<ResumenRuta> { backStackEntry ->
            val resumenDatos = backStackEntry.toRoute<ResumenRuta>()

            ResumenScreen(
                datos = resumenDatos,
                onVolverInicio = {
                    // Requisito: Limpiar la pila de pantallas usando popUpTo y launchSingleTop
                    navController.navigate(FormularioRuta) {
                        popUpTo(FormularioRuta) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}