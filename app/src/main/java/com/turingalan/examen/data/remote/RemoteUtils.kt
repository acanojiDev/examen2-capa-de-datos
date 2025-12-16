package com.turingalan.examen.data.remote

/**
 * Utilidades para ob
 */

fun createQuery(search:String) = "title:$search"

fun getWorkId(key:String) = key.removePrefix("/works/")
