package com.example.pa.comparators

import com.example.pa.data.Product

class ProductNameComparatorReverse: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
    if (p0 != null && p1 != null) {
        return p1.name.compareTo(p0.name)
    }
    return 0
}
}