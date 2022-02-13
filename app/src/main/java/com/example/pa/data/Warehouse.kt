package com.example.pa.data

import com.example.pa.R
import java.time.LocalDate

class Warehouse {
    companion object {
        var products: ArrayList<Product> = ArrayList()
        var called: Int = 0
    }
    fun createProducts() {
        if (called == 0) {
            val imageList = ArrayList<Int>()
            val reviewList = ArrayList<Review>()
            imageList.add(R.drawable.meltingface)
            reviewList.add(Review("Human", "Tastes like your mom", 9, LocalDate.of(2022, 2, 19)))
            products.add(Product("FOOD", 2.00, "Iceceraem", "Snowman", "creamy", "Russia", LocalDate.of(2099, 9, 20), 2.0, 5, imageList, reviewList, 20))
            called = 1
        }
    }
}