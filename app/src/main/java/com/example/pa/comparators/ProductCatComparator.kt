package com.example.pa.comparators

import com.example.pa.data.Product
//for sorting products by category(a-z) then name(a-z)
class ProductCatComparator: Comparator<Product> {
    override fun compare(p0: Product?, p1: Product?): Int {
        if (p0 != null && p1 != null) {
            if (p0.category.compareTo(p1.category) > 0) {
                return 1
            }
            else if (p0.category.compareTo(p1.category) < 0) {
                return -1
            }
            else {
                return p0.name.compareTo(p1.name)
            }
        }
        return 0
    }
}