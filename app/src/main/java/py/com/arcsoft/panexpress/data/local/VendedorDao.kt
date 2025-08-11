package py.com.arcsoft.panexpress.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VendedorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vendedor: VendedorEntity)

    @Query("SELECT * FROM vendedor LIMIT 1")
    fun getVendedor(): Flow<VendedorEntity?>

    @Query("DELETE FROM vendedor")
    suspend fun deleteAll()
}
