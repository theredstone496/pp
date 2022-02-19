package com.example.pa.comparators

import com.example.pa.data.Review

class ReviewNameComparator: Comparator<Review> {
    override fun compare(p0: Review?, p1: Review?): Int {
        if (p0 != null && p1 != null) {
            return p0.name.compareTo(p1.name)
        }
        return 0
    }
}