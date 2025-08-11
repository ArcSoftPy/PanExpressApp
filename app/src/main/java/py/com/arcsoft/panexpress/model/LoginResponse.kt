package py.com.arcsoft.panexpress.model

data class LoginResponse(
    val vendedor: Vendedor
)

data class Vendedor(
    val id: Int,
    val nombre: String,
    val email: String,
    val token: String
)
