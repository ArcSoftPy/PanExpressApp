package py.com.arcsoft.panexpress.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import py.com.arcsoft.panexpress.data.repository.VendedorRepository
import py.com.arcsoft.panexpress.ui.home.HomeScreen
import py.com.arcsoft.panexpress.ui.login.LoginScreen
import py.com.arcsoft.panexpress.ui.splash.SplashScreen
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier

@Composable
fun NavigationGraph(repository: VendedorRepository) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(navController, repository)
        }
        composable(Routes.LOGIN) {
            LoginScreen(navController, repository)
        }
        composable(Routes.HOME) {
            HomeScreen(navController, repository)
        }

        // Pantallas de sincronización
        composable(Routes.SYNC) { TextScreen("Sincronizar datos") }
        composable(Routes.SYNC_CARGAS) { TextScreen("Sincronizar cargas") }
        composable(Routes.SYNC_CLIENTES) { TextScreen("Sincronizar clientes") }

        // Pantallas de clientes
        composable(Routes.CLIENTES) { TextScreen("Clientes") }
        composable(Routes.CLIENTES_AGREGAR) { TextScreen("Agregar cliente") }
        composable(Routes.CLIENTES_MODIFICAR) { TextScreen("Modificar cliente") }

        // Otras pantallas
        composable(Routes.VENTAS) { TextScreen("Ventas") }
        composable(Routes.DEVOLUCIONES) { TextScreen("Devoluciones") }
        composable(Routes.PRODUCTOS) { TextScreen("Productos") }
    }
}

@Composable
fun TextScreen(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text)
    }
}
