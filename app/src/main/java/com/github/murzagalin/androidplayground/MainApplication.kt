package com.github.murzagalin.androidplayground

import android.app.Application
import androidx.room.Room
import com.github.murzagalin.androidplayground.db.AppDatabase
import com.github.murzagalin.androidplayground.db.User
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@HiltAndroidApp
class MainApplication: Application() {


    private val appScope = MainScope()

    override fun onCreate() {
        super.onCreate()

        /*appScope.launch {
            listOf(
                User(username = "JohnDoe", email = "john@example.com"),
                User(username = "Azamat", email = "azamat@example.com"),
                User(username = "Lily", email = "lily@example.com"),
                User(username = "Ildar", email = "ildar@example.com"),
                User(username = "Ainur", email = "ainur@example.com"),
                User(username = "Amir", email = "amir@example.com")
            ).forEach {
                database.userDao().insertUser(it)
            }
        }*/
    }

}