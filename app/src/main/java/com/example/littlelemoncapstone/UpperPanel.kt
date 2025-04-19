package com.example.littlelemoncapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemoncapstone.ui.theme.LittleLemonColor

@Composable
fun UpperPanel(searchPhrase: String, onSearchChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .background(LittleLemonColor.green)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.restaurant_name),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )
        Text(
            text = stringResource(R.string.city),
            fontSize = 24.sp,
            color = LittleLemonColor.cloud
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.short_description),
                style = MaterialTheme.typography.bodyLarge,
                color = LittleLemonColor.cloud,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        TextField(
            value = searchPhrase,
            onValueChange = onSearchChange,
            placeholder = { Text("Enter search phrase") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(8.dp)),
            singleLine = true
        )
    }
}
