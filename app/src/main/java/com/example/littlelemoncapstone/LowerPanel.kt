package com.example.littlelemoncapstone

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.littlelemoncapstone.ui.theme.LittleLemonColor
import java.util.Locale

@Composable
fun LowerPanel(
    menuItems: List<MenuItemRoom>,
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String?) -> Unit
) {
    Column {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            FilterChip(
                selected = selectedCategory == null,
                onClick = { onCategorySelected(null) },
                label = {
                    Text(
                        text = "All",
                        color = if (selectedCategory == null) LittleLemonColor.cloud else LittleLemonColor.charcoal
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = LittleLemonColor.green,
                    containerColor = LittleLemonColor.cloud
                )
            )
            categories.forEach { category ->
                val formattedCategory = category.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
                Spacer(modifier = Modifier.size(8.dp))
                FilterChip(
                    selected = selectedCategory.equals(category, ignoreCase = true),
                    onClick = { onCategorySelected(category) },
                    label = {
                        Text(
                            text = formattedCategory,
                            color = if (selectedCategory.equals(category, ignoreCase = true)) LittleLemonColor.cloud else LittleLemonColor.charcoal
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = LittleLemonColor.green,
                        containerColor = LittleLemonColor.cloud
                    )
                )
            }
        }

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
        ) {
            items(menuItems) { item ->
                Column {
                    MenuItem(item)
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = LittleLemonColor.cloud
                    )
                }
            }
        }
    }
}

@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = menuItem.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = menuItem.description,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$${menuItem.price}", fontWeight = FontWeight.SemiBold)
        }
        AsyncImage(
            model = menuItem.image,
            contentDescription = menuItem.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}
