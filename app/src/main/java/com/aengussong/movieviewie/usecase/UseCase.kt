package com.aengussong.movieviewie.usecase

import com.aengussong.movieviewie.model.Movie
import com.aengussong.movieviewie.repo.global.model.ServiceMovieModel
import com.aengussong.movieviewie.repo.local.model.MovieEntity
import com.aengussong.movieviewie.util.Utils
import java.util.*

open class UseCase {

    protected fun ServiceMovieModel.mapToDb(): MovieEntity {
        val id = itemId ?: 0
        val name = this.name ?: ""
        val image = this.image ?: ""
        val description = this.description ?: ""
        val time = time ?: Date().time
        return MovieEntity(id, name, image, description, time)
    }

    protected fun MovieEntity.mapToUi(): Movie {
        val time = Utils.convertTime(this.time)
        return Movie(name, image, description, time)
    }
}