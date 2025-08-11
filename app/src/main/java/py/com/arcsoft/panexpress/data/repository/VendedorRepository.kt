package py.com.arcsoft.panexpress.data.repository

import kotlinx.coroutines.flow.Flow
import py.com.arcsoft.panexpress.data.local.VendedorDao
import py.com.arcsoft.panexpress.data.local.VendedorEntity
import py.com.arcsoft.panexpress.data.remote.RetrofitInstance
import py.com.arcsoft.panexpress.model.LoginRequest

class VendedorRepository(private val dao: VendedorDao) {

    fun getVendedor(): Flow<VendedorEntity?> = dao.getVendedor()

    suspend fun loginAndSave(email: String, documento: String) {
        val response = RetrofitInstance.api.login(LoginRequest(email, documento))
        val vendedor = response.vendedor
        dao.insert(
            VendedorEntity(
                id = vendedor.id,
                nombre = vendedor.nombre,
                email = vendedor.email,
                token = vendedor.token,
                zona_id = vendedor.zona_id,
                documento = documento,
                lastLoginTime = System.currentTimeMillis()
            )
        )
    }

    suspend fun validateOfflineLogin(email: String, documento: String): Boolean {
        val vendedor = dao.getVendedorOnce()
        if (vendedor != null) {
            val diffMinutes = (System.currentTimeMillis() - vendedor.lastLoginTime) / 60000
            return vendedor.email == email &&
                    vendedor.documento == documento &&
                    diffMinutes <= 30
        }
        return false
    }

    suspend fun saveLastLoginTime(id: Int, time: Long) {
        val vendedor = dao.getVendedorOnce()
        if (vendedor != null) {
            dao.insert(vendedor.copy(lastLoginTime = time))
        }
    }

}
