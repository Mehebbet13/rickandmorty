package com.example.abbtechtestapplication.models

data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String? = null
)