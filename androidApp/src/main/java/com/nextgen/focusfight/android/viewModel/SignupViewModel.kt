package com.nextgen.focusfight.android.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextgen.focusfight.ApiClient
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    var email by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var signupSuccess by mutableStateOf(false)

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun validate(): Boolean {
        return email.isNotBlank() && phoneNumber.isNotBlank() && password.isNotBlank()
    }

    fun submitSignup(onResult: (Boolean, String) -> Unit) {
        if (!validate()) {
            Log.w("SignupViewModel", "Validation failed: one or more fields are empty.")
            onResult(false, "Please fill all fields.")
            return
        }

        isLoading = true
        errorMessage = null

        Log.d("SignupViewModel", "Starting signup request with email: $email, phone: $phoneNumber")

        viewModelScope.launch {
            try {
                val api = ApiClient()
                val result = api.signup(email, phoneNumber, password)

                Log.d("SignupViewModel", "Signup API response: ${result.message}")

                if (result.message.contains("already exists", ignoreCase = true)) {
                    signupSuccess = false
                    Log.w("SignupViewModel", "Account already exists for email: $email")
                    onResult(false, result.message)
                } else {
                    signupSuccess = true
                    Log.i("SignupViewModel", "Signup successful for email: $email")
                    onResult(true, result.message)
                }
            } catch (e: Exception) {
                errorMessage = "Signup failed: ${e.message}"
                signupSuccess = false
                Log.e("SignupViewModel", "Signup error", e)
                onResult(false, errorMessage ?: "Signup failed")
            } finally {
                isLoading = false
                Log.d("SignupViewModel", "Signup process finished. isLoading: $isLoading")
            }
        }
    }
}
