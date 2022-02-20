package com.example.pa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
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
//tab containing the app reviews
class SomethingTab : Fragment() {
    //the list of app reviews
    private var revList = ArrayList<Review>()
    //ui elements
    private lateinit var reviewView2: RecyclerView
    private lateinit var adapter: ReviewRecyclerAdapter
    private lateinit var appReviewSort: ImageButton
    private lateinit var addAppReview: ImageButton
    private lateinit var appRatingView: TextView
    private lateinit var appRatingBar: RatingBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_somethingtab, container, false)
        //gets the list of app reviews
        revList = Warehouse.appRevList
        //initialize the recycler view for the app reviews
        reviewView2 = view.findViewById(R.id.reviewRecycleView2)
        val layoutManager = LinearLayoutManager(this.context)
        reviewView2.layoutManager = layoutManager
        adapter = ReviewRecyclerAdapter(revList)
        reviewView2.adapter = adapter
        //the sort button creates a dialog with sort options
        appReviewSort = view.findViewById(R.id.appReviewSort)
        appReviewSort.setOnClickListener { view2 ->
            //most code is copied from product reviews, but settings are separate
            var builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this.context)
            var contentView: View = this.layoutInflater.inflate(R.layout.rev_sort_dialog, view as ViewGroup, false)
            val sortBtnD: RadioButton = contentView.findViewById(R.id.rSortBtnD)
            val sortBtnN: RadioButton = contentView.findViewById(R.id.rSortBtnN)
            val sortBtnR: RadioButton = contentView.findViewById(R.id.rSortBtnR)
            val sortBtnUp: RadioButton = contentView.findViewById(R.id.rSortBtnUp)
            val sortBtnDown: RadioButton = contentView.findViewById(R.id.rSortBtnDown)
            //initially checks the button based on the setting
            when (Settings.appReviewSortBy) {
                "Date" -> sortBtnD.isChecked = true
                "Name" -> sortBtnN.isChecked = true
                "Rating" -> sortBtnR.isChecked = true
            }
            if (Settings.appReviewSortReverse) sortBtnDown.isChecked = true else sortBtnUp.isChecked = true
            //for each button, unchecks the others of the same type, updates the setting and then sorts
            sortBtnD.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnR.isChecked = false
                Settings.appReviewSortBy = "Date"
                sort(Settings.appReviewSortBy, Settings.appReviewSortReverse, adapter)
            }
            sortBtnN.setOnClickListener { view ->
                sortBtnD.isChecked = false
                sortBtnR.isChecked = false
                Settings.appReviewSortBy = "Name"
                sort(Settings.appReviewSortBy, Settings.appReviewSortReverse, adapter)
            }
            sortBtnR.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnD.isChecked = false
                Settings.appReviewSortBy = "Rating"
                sort(Settings.appReviewSortBy, Settings.appReviewSortReverse, adapter)
            }
            sortBtnUp.setOnClickListener { view ->
                sortBtnDown.isChecked = false
                Settings.appReviewSortReverse = false
                sort(Settings.appReviewSortBy, Settings.appReviewSortReverse, adapter)
            }
            sortBtnDown.setOnClickListener { view ->
                sortBtnUp.isChecked = false
                Settings.appReviewSortReverse = true
                sort(Settings.appReviewSortBy, Settings.appReviewSortReverse, adapter)
            }
            //creates and shows the dialog
            builder.setView(contentView)
            builder.setTitle("Sort By:")
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
        //initial sorting of app reviews
        sort(Settings.appReviewSortBy, Settings.appReviewSortReverse, adapter)
        //button to add app reviews: similar to fab in product page, creates a dialog for user to enter their review
        addAppReview = view.findViewById(R.id.addAppReview)
        addAppReview.setOnClickListener { view2 ->
            var builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this.context)
            var contentView: View = this.layoutInflater.inflate(R.layout.write_review_layout, view as ViewGroup, false)
            val nameText: EditText = contentView.findViewById(R.id.nameText)
            val ratingBar: RatingBar = contentView.findViewById(R.id.ratingBar)
            val contentText: EditText = contentView.findViewById(R.id.reviewContentText)
            builder.setView(contentView)
            builder.setTitle("Write a Review")
            builder.setNegativeButton("Cancel", null)
            //when the post button is clicked, a review is created based on the information the user entered
            builder.setPositiveButton("Post", {dialog, which ->
                Warehouse.appRevList.add(Review(nameText.text.toString(), contentText.text.toString(), (ratingBar.rating * 2).toInt() ,  LocalDate.now()))
                adapter.notifyDataSetChanged()
                //the average app rating is updated and the review is sorted into the right position
                updateRating()
                sort(Settings.appReviewSortBy, Settings.appReviewSortReverse, adapter)
            })
            builder.create().show()
        }
        appRatingView = view.findViewById(R.id.appRatingView)
        appRatingBar = view.findViewById(R.id.appRatingBar)
        //calculate the average app rating and show it
        updateRating()
        return view
    }
    //sorting function
    fun sort(sortingMode: String, reverse: Boolean, adapter: ReviewRecyclerAdapter) {
        //sort based on the setting and then reverse based on the setting
        when (sortingMode) {
            "Date" -> Collections.sort(adapter.revList, ReviewDateComparator())
            "Name" -> Collections.sort(adapter.revList, ReviewNameComparator())
            "Rating" -> Collections.sort(adapter.revList, ReviewRatingComparator())
        }
        if (reverse) adapter.revList.reverse()
        adapter.notifyDataSetChanged()
    }
    //calculate the average rating of the app and update the views accordingly
    fun updateRating() {
        var rating = 0.0
        for (i in 0..Warehouse.appRevList.size-1) {
            rating += Warehouse.appRevList[i].rating
        }
        if (Warehouse.appRevList.size != 0) {
            rating = rating/2/Warehouse.appRevList.size
            appRatingBar.rating = rating.toFloat()
            appRatingView.text = String.format("%.1f/5 (", rating) + Warehouse.appRevList.size + " ratings)"
        }
        else {

            appRatingView.text =
                "(No ratings)"
        }
    }
}