package py.com.arcsoft.panexpress.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import py.com.arcsoft.panexpress.data.repository.VendedorRepository
import py.com.arcsoft.panexpress.navigation.Routes
import androidx.navigation.NavController

data class DashboardItem(val title: String, val route: String)

@Composable
fun HomeScreen(navController: NavController, repository: VendedorRepository) {
    var nombre by remember { mutableStateOf("") }
    var zona_id by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        repository.getVendedor().collectLatest {
            nombre = it?.nombre ?: ""
            zona_id = (it?.zona_id ?: "").toString()
        }
    }

    val items = listOf(
        DashboardItem("Sincronizar datos", Routes.SYNC),
        DashboardItem("Clientes", Routes.CLIENTES),
        DashboardItem("Ventas", Routes.VENTAS),
        DashboardItem("Devoluciones", Routes.DEVOLUCIONES),
        DashboardItem("Productos", Routes.PRODUCTOS)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Bienvenido $nombre", style = MaterialTheme.typography.headlineSmall)
        Text("Zona: $zona_id", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clickable { navController.navigate(item.route) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(item.title, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}
