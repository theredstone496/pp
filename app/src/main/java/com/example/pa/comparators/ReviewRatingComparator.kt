package com.example.pa.comparators

import com.example.pa.data.Review
//for sorting reviews by rating(increasing)
class ReviewRatingComparator: Comparator<Review> {
    override fun compare(p0: Review?, p1: Review?): Int {
        if (p0 != null && p1 != null) {
            if (p0.rating < p1.rating) {
                return -1
            }
            else if (p0.rating > p1.rating) {
                return 1
            }
            return 0
        }
        return 0
    }
}