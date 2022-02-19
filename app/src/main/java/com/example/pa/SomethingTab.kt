package com.example.pa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.comparators.ReviewDateComparator
import com.example.pa.comparators.ReviewNameComparator
import com.example.pa.comparators.ReviewRatingComparator
import com.example.pa.data.Review
import com.example.pa.data.Settings
import com.example.pa.data.Warehouse
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class SomethingTab : Fragment() {
    private var revList = ArrayList<Review>()
    private lateinit var reviewView2: RecyclerView
    private lateinit var adapter: ReviewRecyclerAdapter
    private lateinit var appReviewSort: ImageButton
    private lateinit var appRatingView: TextView
    private lateinit var appRatingBar: RatingBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_somethingtab, container, false)
        revList = Warehouse.appRevList
        reviewView2 = view.findViewById(R.id.reviewRecycleView2)
        val layoutManager = LinearLayoutManager(this.context)
        reviewView2.layoutManager = layoutManager
        adapter = ReviewRecyclerAdapter(revList)
        reviewView2.adapter = adapter
        appReviewSort = view.findViewById(R.id.appReviewSort)
        appReviewSort.setOnClickListener { view2 ->
            var builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this.context)
            var contentView: View = this.layoutInflater.inflate(R.layout.rev_sort_dialog, view as ViewGroup, false)
            val sortBtnD: RadioButton = contentView.findViewById(R.id.rSortBtnD)
            val sortBtnN: RadioButton = contentView.findViewById(R.id.rSortBtnN)
            val sortBtnR: RadioButton = contentView.findViewById(R.id.rSortBtnR)
            val sortBtnUp: RadioButton = contentView.findViewById(R.id.rSortBtnUp)
            val sortBtnDown: RadioButton = contentView.findViewById(R.id.rSortBtnDown)
            when (Settings.reviewSortBy) {
                "Date" -> sortBtnD.isChecked = true
                "Name" -> sortBtnN.isChecked = true
                "Rating" -> sortBtnR.isChecked = true
            }
            if (Settings.reviewSortReverse) sortBtnDown.isChecked = true else sortBtnUp.isChecked = true
            sortBtnD.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnR.isChecked = false
                Settings.reviewSortBy = "Date"
                sort(Settings.reviewSortBy, Settings.reviewSortReverse, adapter)
            }
            sortBtnN.setOnClickListener { view ->
                sortBtnD.isChecked = false
                sortBtnR.isChecked = false
                Settings.reviewSortBy = "Name"
                sort(Settings.reviewSortBy, Settings.reviewSortReverse, adapter)
            }
            sortBtnR.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnD.isChecked = false
                Settings.reviewSortBy = "Rating"
                sort(Settings.reviewSortBy, Settings.reviewSortReverse, adapter)
            }
            sortBtnUp.setOnClickListener { view ->
                sortBtnDown.isChecked = false
                Settings.reviewSortReverse = false
                sort(Settings.reviewSortBy, Settings.reviewSortReverse, adapter)
            }
            sortBtnDown.setOnClickListener { view ->
                sortBtnUp.isChecked = false
                Settings.reviewSortReverse = true
                sort(Settings.reviewSortBy, Settings.reviewSortReverse, adapter)
            }
            builder.setView(contentView)
            builder.setTitle("Sort By:")
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
        sort(Settings.reviewSortBy, Settings.reviewSortReverse, adapter)
        appRatingView = view.findViewById(R.id.appRatingView)
        appRatingBar = view.findViewById(R.id.appRatingBar)
        var rating = 0.0
        for (i in 0..Warehouse.appRevList.size-1) {
            rating += Warehouse.appRevList[i].rating
        }
        if (Warehouse.appRevList.size != 0) {
            rating = rating/2/Warehouse.appRevList.size
            appRatingBar.rating = rating.toFloat()
            appRatingView.text = String.format("%.1f/5 (", rating) + Warehouse.appRevList.size + " ratings)"
        }
        return view
    }
    fun sort(sortingMode: String, reverse: Boolean, adapter: ReviewRecyclerAdapter) {
        when (sortingMode) {
            "Date" -> Collections.sort(adapter.revList, ReviewDateComparator())
            "Name" -> Collections.sort(adapter.revList, ReviewNameComparator())
            "Rating" -> Collections.sort(adapter.revList, ReviewRatingComparator())
        }
        if (reverse) adapter.revList.reverse()
        adapter.notifyDataSetChanged()
    }
}