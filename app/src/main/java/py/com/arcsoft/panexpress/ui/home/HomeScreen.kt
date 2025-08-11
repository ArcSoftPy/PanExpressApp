package py.com.arcsoft.panexpress.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import py.com.arcsoft.panexpress.data.repository.VendedorRepository

@Composable
fun HomeScreen(repository: VendedorRepository) {
    var nombre by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        repository.getVendedor().collectLatest {
            nombre = it?.nombre ?: ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido $nombre", style = MaterialTheme.typography.headlineMedium)
    }
}
