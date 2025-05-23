package com.nextgen.focusfight.android.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextgen.focusfight.android.R
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFCFC)),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(R.drawable.ic_welcome_screen),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
        )

        Text(
            text = stringResource(R.string.welcome_to),
            fontSize = 50.sp,
            color = Color(0xFF424242),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 24.dp, top = 8.dp, bottom = 4.dp) // control spacing
        )

        Text(
            text = stringResource(R.string.focusfight),
            fontSize = 50.sp,
            color = Color(0xFF424242),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 24.dp)
        )

        Text(
            text = stringResource(R.string.subtitle),
            fontSize = 20.sp,
            color = Color(0xFF9E9E9E),
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(start = 24.dp, top = 4.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable {
                    navController.navigate("login_screen")
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = stringResource(R.string.continueBtn),
                    fontSize = 20.sp,
                    color = Color(0xFF9E9E9E),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Image(
                    painter = painterResource(R.drawable.ic_button),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(46.dp)
                        .height(36.dp)
                )
            }
        }
    }
}