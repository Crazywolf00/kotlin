package com.example.kotlinapp.util

import com.example.kotlinapp.model.Movie
import com.example.kotlinapp.model.MovieDTO
import org.springframework.stereotype.Component

@Component
class MovieMapper : Mapper<MovieDTO, Movie> {

    override fun fromEntity(entity: Movie): MovieDTO = MovieDTO(
        entity.id,
        entity.name,
        entity.author,
        entity.mainActor,
        entity.ratingCSFD,
        entity.myRating,
        entity.year
    )


    override fun toEntity(domain: MovieDTO): Movie = Movie(
        domain.id,
        domain.name,
        domain.author,
        domain.mainActor,
        domain.ratingCSFD,
        domain.myRating,
        domain.year
    )


}