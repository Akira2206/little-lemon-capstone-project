package com.example.littlelemoncapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TopAppBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.little_lemon_logo),
            contentDescription = "Logo",
            modifier = Modifier.height(40.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(ProfileDestination.route)
                }
        )
    }
}
