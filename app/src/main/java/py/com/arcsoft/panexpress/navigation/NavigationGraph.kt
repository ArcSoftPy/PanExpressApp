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
            HomeScreen(repository)
        }
    }
}
