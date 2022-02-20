package com.example.pa.data

import com.example.pa.data.Review
import java.time.LocalDate
//represents a single product in the display, also stores its stock and reviews assigned to it
data class Product(val category: String, val price: Double, val name: String, val brand: String,
                   val desc: String, val country: String, val expiry: LocalDate,
                   val mass: Double,  val temp: Int, val imageList: ArrayList<Int>,
                   val reviewList: ArrayList<Review>, var stock: Int)