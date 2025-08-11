package py.com.arcsoft.panexpress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import py.com.arcsoft.panexpress.data.local.AppDatabase
import py.com.arcsoft.panexpress.data.repository.VendedorRepository
import py.com.arcsoft.panexpress.navigation.NavigationGraph
import py.com.arcsoft.panexpress.ui.theme.PanExpressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar DB y repo
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "panexpress_db"
        ).build()

        val repository = VendedorRepository(db.vendedorDao())

        setContent {
            PanExpressTheme {
                NavigationGraph(repository = repository)
            }
        }
    }
}
