package com.nextgen.focusfight.android.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextgen.focusfight.android.R
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFCFC)),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(R.drawable.ic_welcome_screen), // Replace with correct resource handling
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
        )

        Text(
            text = "Welcome To",
            fontSize = 50.sp,
            color = Color(0xFF424242),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 24.dp)
                .offset(y = (-60).dp)
        )

        Text(
            text = "FocusFight",
            fontSize = 45.sp,
            color = Color(0xFF424242),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 24.dp)
                .offset(y = (-10).dp)
        )
    }
}