package com.example.pa.data
//stores some sort/filter settings
class Settings {
    companion object {
        //search text
        var search: String = ""
        //which category of product is being shown
        var viewedCategory: String = "All"
        //product sort option
        var sortBy: String = "Category"
        //review sort option
        var reviewSortBy: String = "Date"
        //whether the product sorting is reversed (e.g. a-z becomes z-a)
        var sortReverse = false
        //whether the review sorting is reversed
        var reviewSortReverse = true
        //separate review sorting options for app reviews
        var appReviewSortBy: String = "Date"
        var appReviewSortReverse = true
        //layout in product recycler view (grid/linear)
        var grid = true
    }
}