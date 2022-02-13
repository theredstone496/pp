package com.example.pa

import com.example.pa.data.Review
import java.time.LocalDate

data class Product(val category: String, val price: Double, val name: String, val brand: String,
                   val desc: String, val country: String, val expiry: LocalDate,
                   val mass: Double,  val temp: Int, val imageList: ArrayList<Int>,
                   val reviewList: ArrayList<Review>)