package py.com.arcsoft.panexpress.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import py.com.arcsoft.panexpress.data.repository.VendedorRepository
import py.com.arcsoft.panexpress.navigation.Routes

@Composable
fun SplashScreen(navController: NavController, repository: VendedorRepository) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }

    LaunchedEffect(Unit) {
        delay(1500) // Simula carga
        val vendedor = repository.getVendedor().first()
        if (vendedor != null) {
            navController.navigate(Routes.HOME) {
                popUpTo(Routes.SPLASH) { inclusive = true }
            }
        } else {
            navController.navigate(Routes.LOGIN) {
                popUpTo(Routes.SPLASH) { inclusive = true }
            }
        }
    }
}
