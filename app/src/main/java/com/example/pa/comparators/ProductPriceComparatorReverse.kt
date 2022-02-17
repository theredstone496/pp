package com.example.pa.comparators

import com.example.pa.data.Product

class ProductPriceComparatorReverse: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            if (p0.price < p1.price) {
                return 1
            }
            else if (p0.price > p1.price) {
                return -1
            }
            return 0
        }
        return 0
    }
}