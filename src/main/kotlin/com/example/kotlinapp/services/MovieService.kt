package com.example.kotlinapp.services

import com.example.kotlinapp.model.MovieDTO

interface MovieService {

    fun createMovie(movieDTO: MovieDTO): MovieDTO

    fun getMovie(id: Int): MovieDTO

    fun getAllMovies(): List<MovieDTO>

    fun updateMovie(movieDTO: MovieDTO): MovieDTO

    fun deleteMovie(id: Int)
}