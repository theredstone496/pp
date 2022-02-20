package com.example.pa.comparators

import com.example.pa.data.Product
//for sorting products by brand name (a-z)
class ProductBrandComparator: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            return p0.brand.compareTo(p1.brand)
        }
        return 0
    }
}