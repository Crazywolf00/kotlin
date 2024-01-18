package com.example.kotlinapp.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    var name: String,
    var author: String,
    var mainActor: String,
    var ratingCSFD: Int,
    var myRating: Int,
    var year: Int
)
