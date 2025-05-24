package com.nextgen.focusfight.android.viewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextgen.focusfight.ApiClient
import com.nextgen.focusfight.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)
    var rememberMe by mutableStateOf(false)

    var isLoading by mutableStateOf(false)
    var loginSuccess by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var loggedInUser by mutableStateOf<LoginResponse?>(null)

    private val apiClient = ApiClient()

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun toggleRememberMe() {
        rememberMe = !rememberMe
    }

    fun validate(): Boolean {
        return email.isNotBlank() && password.length >= 6
    }

    fun submitLogin(onResult: (Boolean, String) -> Unit) {
        if (!validate()) {
            onResult(false, "Please enter a valid email and password.")
            return
        }

        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val response = apiClient.login(email, password)

                if (response.user != null) {
                    // User found - login success
                    loggedInUser = response
                    loginSuccess = true
                    onResult(true, "Login Successful")
                } else {
                    loginSuccess = false
                    onResult(false, response.message.ifBlank { "Account not created." })
                }
            } catch (e: Exception) {
                errorMessage = "Login failed: ${e.localizedMessage}"
                loginSuccess = false
                onResult(false, errorMessage ?: "Login failed")
            } finally {
                isLoading = false
            }
        }
    }
}
