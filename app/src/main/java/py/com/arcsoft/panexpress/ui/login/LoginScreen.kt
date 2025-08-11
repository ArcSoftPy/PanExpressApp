package py.com.arcsoft.panexpress.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import py.com.arcsoft.panexpress.data.repository.VendedorRepository
import py.com.arcsoft.panexpress.navigation.Routes

@Composable
fun LoginScreen(navController: NavController, repository: VendedorRepository) {
    var email by remember { mutableStateOf("") }
    var documento by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = documento,
            onValueChange = { documento = it },
            label = { Text("Documento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        val coroutineScope = rememberCoroutineScope()

        Button(
            onClick = {
                loading = true
                error = null
                coroutineScope.launch {
                    try {
                        var loggedIn = false
                        try {
                            repository.loginAndSave(email, documento) // intenta online
                            loggedIn = true
                        } catch (e: Exception) {
                            // Si falla, intenta offline
                            if (repository.validateOfflineLogin(email, documento)) {
                                loggedIn = true
                            } else {
                                error = "Credenciales incorrectas o sesión expirada"
                            }
                        }
                        if (loggedIn) {
                            // Si fue offline, actualizar la hora del último login
                            val vendedor = repository.getVendedor().first()
                            if (vendedor != null) {
                                repository.saveLastLoginTime(vendedor.id, System.currentTimeMillis())
                            }
                            navController.navigate(Routes.HOME) {
                                popUpTo(Routes.LOGIN) { inclusive = true }
                            }
                        }
                    } finally {
                        loading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }

        if (loading) {
            Spacer(modifier = Modifier.height(20.dp))
            CircularProgressIndicator()
        }

        error?.let {
            Spacer(modifier = Modifier.height(10.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
