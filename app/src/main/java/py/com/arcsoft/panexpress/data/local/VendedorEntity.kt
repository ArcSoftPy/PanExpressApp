package py.com.arcsoft.panexpress.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendedor")
data class VendedorEntity(
    @PrimaryKey val id: Int,
    val nombre: String,
    val email: String,
    val token: String
)
