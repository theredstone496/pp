package com.example.pa.data

import java.time.LocalDate
//represents a single review for a product or for the app
data class Review(val name: String, val content: String, val rating: Int, val date: LocalDate) {
}