package com.example.littlelemoncapstone

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemoncapstone.ui.theme.LittleLemonCapstoneTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val client by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "little_lemon_db"
        ).build()

        setContent {
            LittleLemonCapstoneTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
                val isRegistered = sharedPref.getString("firstName", null) != null

                val startDestination = if (isRegistered) HomeDestination.route else OnboardingDestination.route

                NavHost(navController = navController, startDestination = startDestination) {
                    composable(OnboardingDestination.route) {
                        Onboarding(navController)
                    }
                    composable(HomeDestination.route) {
                        Home(navController)
                    }
                    composable(ProfileDestination.route) {
                        Profile(navController)
                    }
                }
            }
        }

        fetchAndStoreMenu()
    }

    private fun fetchAndStoreMenu() {
        lifecycleScope.launch {
            val isEmpty = database.menuItemDao().isEmpty()
            if (isEmpty) {
                try {
                    val menuNetwork = fetchMenu()
                    val menuItemsRoom = menuNetwork.menu.map { it.toMenuItemRoom() }.toTypedArray()
                    database.menuItemDao().insertAll(*menuItemsRoom)
                    Log.d("MainActivity", "Saved ${menuItemsRoom.size} items to database")
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error fetching/saving menu", e)
                }
            } else {
                Log.d("MainActivity", "Menu already in database")
            }
        }
    }

    private suspend fun fetchMenu(): MenuNetwork {
        val url = "https://api.myjson.online/v1/records/63d9abd8-db8d-40cb-9951-6fa6242b32f8"
        val response: MenuResponseWrapper = client.get(url).body()
        return response.data
    }
}
