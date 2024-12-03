package com.bibin.babu.software.developer.peopleinneedcoding.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class AppDateBase : RoomDatabase() {
    abstract val userDao: UserDao
}