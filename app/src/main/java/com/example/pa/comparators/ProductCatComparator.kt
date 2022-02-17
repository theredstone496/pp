package com.example.pa.comparators

import com.example.pa.data.Product

class ProductCatComparator: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            return p0.category.compareTo(p1.category)
        }
        return 0
    }
}