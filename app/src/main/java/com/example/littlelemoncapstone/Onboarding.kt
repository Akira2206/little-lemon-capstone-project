package com.example.littlelemoncapstone

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Onboarding(navController: NavHostController) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(top = 8.dp),
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFF495E57))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                fontSize = 24.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Personal information",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                if (firstName.text.isBlank() || lastName.text.isBlank() || email.text.isBlank()) {
                    Toast.makeText(
                        context,
                        "Registration unsuccessful. Please enter all data.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("firstName", firstName.text)
                        putString("lastName", lastName.text)
                        putString("email", email.text)
                        apply()
                    }
                    Toast.makeText(
                        context,
                        "Registration successful!",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate("Home") {
                        popUpTo("Onboarding") { inclusive = true }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
            border = BorderStroke(1.dp, color = Color(0xFF9E661B)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp)
        ) {
            Text("Register", color = Color.Black, fontSize = 16.sp)
        }
    }
}
