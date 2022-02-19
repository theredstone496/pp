package com.example.pa

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.pa.comparators.*
import com.example.pa.data.Product
import com.example.pa.data.Review
import com.example.pa.data.Settings
import com.example.pa.data.Warehouse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator3
import java.text.NumberFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

//to view more product details
class ProductActivity : AppCompatActivity() {
    private lateinit var mainLayout: ViewGroup
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private lateinit var catView: TextView
    private lateinit var nameView: TextView
    private lateinit var brandView: TextView
    private lateinit var descView: TextView
    private lateinit var priceView: TextView
    private lateinit var switch: Switch
    private lateinit var infoView: TextView
    private lateinit var pReviewSort: ImageButton
    private lateinit var reviewView: RecyclerView
    private lateinit var adapter: ReviewRecyclerAdapter
    private lateinit var ratingBar: RatingBar
    private lateinit var ratingView: TextView
    private lateinit var fab: FloatingActionButton
    private lateinit var product: Product
    private var revList = ArrayList<Review>()
    private var shown = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        mainLayout = findViewById(R.id.main_product_layout)
        viewPager = findViewById(R.id.viewPager)
        catView = findViewById(R.id.catView)
        nameView = findViewById(R.id.nameView)
        brandView = findViewById(R.id.brandView)
        descView = findViewById(R.id.descView)
        priceView = findViewById(R.id.priceView)
        switch = findViewById(R.id.additionalSwitch)
        infoView = findViewById(R.id.additionalInfoView)
        reviewView = findViewById(R.id.reviewRecycleView)
        ratingBar = findViewById(R.id.ratingBar2)
        pReviewSort = findViewById(R.id.p_review_sort)
        ratingView = findViewById(R.id.ratingView)
        fab = findViewById(R.id.reviewfab)
        indicator = findViewById(R.id.indicator)

        product = Warehouse.products[intent.getIntExtra("index", 0)]
        catView.text = getString(R.string.cat, product.category)
        nameView.text = product.name
        brandView.text = product.brand
        descView.text = product.desc

        viewPager.adapter = ViewPager2Adapter(this, product.imageList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        indicator.setViewPager(viewPager)

        priceView.text = NumberFormat.getCurrencyInstance().format(product.price)
        switch.setOnClickListener { view ->
            if (switch.isChecked) {
                showAdditionalInfo()

            }
            else {
                hideAdditionalInfo()

            }
        }
        revList = product.reviewList
        val layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        reviewView.layoutManager = layoutManager
        adapter = ReviewRecyclerAdapter(revList)
        reviewView.adapter = adapter
        pReviewSort.setOnClickListener { view ->
            var builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
            var contentView: View = this.layoutInflater.inflate(R.layout.rev_sort_dialog, mainLayout, false)
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
        fab.setOnClickListener { view ->
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            var contentView: View = this.layoutInflater.inflate(R.layout.write_review_layout, mainLayout, false)
            val nameText: EditText = contentView.findViewById(R.id.nameText)
            val ratingBar: RatingBar = contentView.findViewById(R.id.ratingBar)
            val contentText: EditText = contentView.findViewById(R.id.reviewContentText)
            builder.setView(contentView)
            builder.setTitle("Write a Review")
            builder.setNegativeButton("Cancel", null)
            builder.setPositiveButton("Post", {dialog, which ->
                revList.add(Review(nameText.text.toString(), contentText.text.toString(), (ratingBar.rating * 2).toInt() ,  LocalDate.now()))
                adapter.notifyDataSetChanged()
                updateRating()
            })
            builder.create().show()
        }
        updateRating()
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        if(imm.isAcceptingText)
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)


        return true
    }
    fun updateRating() {
        var rating = 0.0
        for (i in 0..product.reviewList.size-1) {
            rating += product.reviewList[i].rating
        }
        if (product.reviewList.size != 0) {
            rating = rating/2/product.reviewList.size
            ratingBar.rating = rating.toFloat()
            ratingView.text = String.format("%.1f/5 (", rating) + product.reviewList.size + " ratings)"
        }
        else {

            ratingView.text =
                "(No ratings)"
        }
    }
    fun showAdditionalInfo() {

        infoView.text = "Country of origin: " + product.country + "\n" +
                "Expiry date: " + product.expiry.toString() + "\n" +
                "Product mass: " + NumberFormat.getNumberInstance().format(product.mass) + "kg\n" +
                "Storage temperature: " + product.temp + "°C" + "\n" +
                product.stock + " left in stock"
    }
    fun hideAdditionalInfo() {

        infoView.text = null
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