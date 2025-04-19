package com.example.littlelemoncapstone

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room

class MenuViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "little_lemon_db"
    ).build()

    private val menuDao = db.menuItemDao()

    val menuItems: LiveData<List<MenuItemRoom>> = menuDao.getAll()
}
