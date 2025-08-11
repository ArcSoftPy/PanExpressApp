package py.com.arcsoft.panexpress.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VendedorEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vendedorDao(): VendedorDao
}
