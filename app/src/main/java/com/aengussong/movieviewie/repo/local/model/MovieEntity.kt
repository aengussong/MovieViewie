package com.aengussong.movieviewie.repo.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id:Long,
    val name:String,
    val image:String,
    val description:String,
    val time:Long
)