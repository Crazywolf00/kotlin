package com.example.kotlinapp.model

data class MovieDTO(
    var id: Int = -1,
    var name: String = "default movie",
    var author: String = "nobody",
    var mainActor: String = "nobody",
    var ratingCSFD: Int = 0,
    var myRating: Int = 0,
    var year: Int = 0
)
