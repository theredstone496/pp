package com.example.pa.comparators

import com.example.pa.data.Product

class ProductBrandComparatorReverse: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            return p1.brand.compareTo(p0.brand)
        }
        return 0
    }
}