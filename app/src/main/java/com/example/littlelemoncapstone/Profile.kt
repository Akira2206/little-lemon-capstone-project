package com.example.littlelemoncapstone

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    val firstName = sharedPref.getString("firstName", "") ?: ""
    val lastName = sharedPref.getString("lastName", "") ?: ""
    val email = sharedPref.getString("email", "") ?: ""

    Column(
        modifier = Modifier
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.little_lemon_logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(24.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Personal information",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = {},
            label = { Text("First name") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = {},
            label = { Text("Last name") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text("Email") },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                with(sharedPref.edit()) {
                    clear()
                    apply()
                }
                navController.navigate(OnboardingDestination.route) {
                    popUpTo(ProfileDestination.route) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, color = Color(0xFF9E661B)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp)
        ) {
            Text("Log out", color = Color.Black, fontSize = 16.sp)
        }
    }
}

