package com.bibin.babu.software.developer.peopleinneedcoding.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userModel: UserModel)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<UserModel>>
}