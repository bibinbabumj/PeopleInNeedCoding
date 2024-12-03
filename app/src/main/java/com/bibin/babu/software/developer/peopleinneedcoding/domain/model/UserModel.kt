package com.bibin.babu.software.developer.peopleinneedcoding.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String
)
