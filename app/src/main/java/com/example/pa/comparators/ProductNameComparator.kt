package com.example.pa.comparators

import com.example.pa.data.Product

class ProductNameComparator: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            return p0.name.compareTo(p1.name)
        }
        return 0
    }
}