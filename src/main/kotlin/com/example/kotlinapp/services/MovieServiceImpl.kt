package com.example.kotlinapp.services

import com.example.kotlinapp.model.MovieDTO
import com.example.kotlinapp.repository.MovieRepository
import com.example.kotlinapp.util.MovieMapper
import com.example.kotlinapp.util.exceptions.MovieException
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : MovieService {

    override fun createMovie(movieDTO: MovieDTO): MovieDTO {
        if (movieDTO.id != -1) {
            throw IllegalAccessException("Id must be null or -1.")
        }
        if (movieDTO.ratingCSFD == 0 ||
            movieDTO.name == "default movie" ||
            movieDTO.myRating == 0 ||
            movieDTO.author == "nobody" ||
            movieDTO.year == 0 ||
            movieDTO.mainActor == "nobody") {
            throw MovieException("Complete movie object is expected")
        }
        val movie = movieRepository.save(movieMapper.toEntity(movieDTO))
        return movieMapper.fromEntity(movie)
    }

    override fun getMovie(id: Int): MovieDTO {
        val optionalMovie = movieRepository.findById(id)
        val movie = optionalMovie.orElseThrow { MovieException("Movie with $id is not present.") }
        return movieMapper.fromEntity(movie);
    }

    override fun getAllMovies(): List<MovieDTO> {

        val movies = movieRepository.getAllMovies()
        if (movies.isEmpty()) {
            throw MovieException("List of movies is empty.")
        }
        return movies.map {
            movieMapper.fromEntity(it)
        }
    }

    override fun updateMovie(movieDTO: MovieDTO): MovieDTO {
        val exist = movieRepository.existsById(movieDTO.id)
        if (!exist) {
            throw MovieException("Movie with ${movieDTO.id} is not present.")
        }
        if (movieDTO.ratingCSFD == 0 ||
            movieDTO.name == "default movie" ||
            movieDTO.myRating == 0 ||
            movieDTO.author == "nobody" ||
            movieDTO.year == 0 ||
            movieDTO.mainActor == "nobody") {
            throw MovieException("Complete movie object is expected")
        }
        movieRepository.save(movieMapper.toEntity(movieDTO))
        return movieDTO
    }

    override fun deleteMovie(id: Int) {
        val exist = movieRepository.existsById(id)
        if (!exist) {
            throw MovieException("Movie with $id is not present.")
        }
        movieRepository.deleteById(id)
    }

}