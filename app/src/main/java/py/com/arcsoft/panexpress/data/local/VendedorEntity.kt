package py.com.arcsoft.panexpress.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendedor")
data class VendedorEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val email: String,
    val token: String,
    val zona_id: Int,
    val documento: String, // para validación offline
    val lastLoginTime: Long // milisegundos desde Epoch
)

