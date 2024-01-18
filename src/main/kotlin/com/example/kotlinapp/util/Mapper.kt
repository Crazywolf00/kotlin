package com.example.kotlinapp.util

import com.example.kotlinapp.model.Movie

interface Mapper<D, E> {

    fun fromEntity(entity: Movie): D
    fun toEntity(domain: D): E

}