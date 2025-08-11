package py.com.arcsoft.panexpress.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import py.com.arcsoft.panexpress.data.repository.VendedorRepository

class LoginViewModel(private val repository: VendedorRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginState>(LoginState.Idle)
    val uiState: StateFlow<LoginState> get() = _uiState

    fun login(email: String, documento: String) {
        viewModelScope.launch {
            try {
                _uiState.value = LoginState.Loading
                repository.loginAndSave(email, documento)
                _uiState.value = LoginState.Success
            } catch (e: Exception) {
                _uiState.value = LoginState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
