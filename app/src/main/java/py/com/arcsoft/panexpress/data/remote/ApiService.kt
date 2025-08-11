package py.com.arcsoft.panexpress.data.remote

import py.com.arcsoft.panexpress.model.LoginRequest
import py.com.arcsoft.panexpress.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/vendedor/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
