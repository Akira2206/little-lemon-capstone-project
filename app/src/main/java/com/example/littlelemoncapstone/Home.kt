package com.example.littlelemoncapstone

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: MenuViewModel = viewModel(
        factory = MenuViewModelFactory(context)
    )
    val menuItems by viewModel.menuItems.observeAsState(initial = emptyList())
    var searchPhrase by remember { mutableStateOf("") }
    val categories = menuItems.map { it.category }.distinct()
    var selectedCategory by remember { mutableStateOf<String?>(null) }


    val filteredItems = menuItems.filter {
        (selectedCategory == null || it.category.equals(selectedCategory, ignoreCase = true)) &&
                it.title.contains(searchPhrase, ignoreCase = true)
    }

    Column {
        TopAppBar(navController)
        UpperPanel(searchPhrase) { searchPhrase = it }
        LowerPanel(
            menuItems = filteredItems,
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

    }
}

