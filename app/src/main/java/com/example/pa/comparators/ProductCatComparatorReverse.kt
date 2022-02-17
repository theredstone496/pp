package com.example.pa.comparators

import com.example.pa.data.Product

class ProductCatComparatorReverse: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            return p1.category.compareTo(p0.category)
        }
        return 0
    }
}