package com.example.pa.comparators

import com.example.pa.data.Product
//for sorting products by price (increasing)
class ProductPriceComparator: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            if (p0.price < p1.price) {
                return -1
            }
            else if (p0.price > p1.price) {
                return 1
            }
            return 0
        }
        return 0
    }
}