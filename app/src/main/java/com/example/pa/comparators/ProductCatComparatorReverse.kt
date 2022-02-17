package com.example.pa.comparators

import com.example.pa.data.Product

class ProductCatComparatorReverse: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            if (p1.category.compareTo(p0.category) > 0) {
                return 1
            }
            else if (p1.category.compareTo(p0.category) > 0) {
                return -1
            }
            else {
                return p1.name.compareTo(p0.name)
            }
        }
        return 0
    }
}