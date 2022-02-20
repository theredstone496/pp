package com.example.pa.comparators

import com.example.pa.data.Review
//for sorting reviews by date (earlier to later)
class ReviewDateComparator: Comparator<Review> {
    override fun compare(p0: Review?, p1: Review?): Int {
        if (p0 != null && p1 != null) {
            return p0.date.compareTo(p1.date)
        }
        return 0
    }
}