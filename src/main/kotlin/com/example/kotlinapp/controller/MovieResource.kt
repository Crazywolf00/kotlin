package com.example.kotlinapp.controller

import com.example.kotlinapp.model.MovieDTO
import com.example.kotlinapp.services.MovieService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class MovieResource(
    private val movieService: MovieService
) {

    @GetMapping("")
    fun getAllMovies(): ResponseEntity<List<MovieDTO>> {
        return ResponseEntity.ok(movieService.getAllMovies())
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id: Int): ResponseEntity<MovieDTO> {
        return ResponseEntity(movieService.getMovie(id), HttpStatus.OK)
    }

    @PostMapping("")
    fun createMovie(@RequestBody movieDTO: MovieDTO): ResponseEntity<MovieDTO> {
        return ResponseEntity(movieService.createMovie(movieDTO), HttpStatus.CREATED)
    }

    @PutMapping("")
    fun updateMovie(@RequestBody movieDTO: MovieDTO):  ResponseEntity<MovieDTO> {
        return ResponseEntity.ok(movieService.updateMovie(movieDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteMovie (@PathVariable id: Int): ResponseEntity<Unit> {
        return ResponseEntity(movieService.deleteMovie(id), HttpStatus.NO_CONTENT)
    }


}