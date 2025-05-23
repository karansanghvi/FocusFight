package com.nextgen.focusfight.android.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nextgen.focusfight.android.ui.accountDetails.LoginScreen
import com.nextgen.focusfight.android.ui.accountDetails.SignupScreen
import com.nextgen.focusfight.android.ui.onboarding.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_screen") {
        composable("welcome_screen") { WelcomeScreen(navController) }
        composable("login_screen") { LoginScreen(navController, viewModel = viewModel()) }
        composable("signup_screen") { SignupScreen(navController, viewModel = viewModel()) }
    }
}
