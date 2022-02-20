package com.example.pa

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.pa.comparators.*
import com.example.pa.data.Product
import com.example.pa.data.Review
import com.example.pa.data.Settings
import com.example.pa.data.Warehouse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import me.relex.circleindicator.CircleIndicator3
import java.text.NumberFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import androidx.core.view.isVisible


//to view more product details
class ProductActivity : AppCompatActivity() {
    //all ui elements
    private lateinit var mainLayout: ViewGroup
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private lateinit var nameView: TextView
    private lateinit var brandView: TextView
    private lateinit var descView: TextView
    private lateinit var priceView: TextView
    private lateinit var moreInfoButton: Button
    private lateinit var infoView: TextView
    private lateinit var pReviewSort: ImageButton
    private lateinit var reviewView: RecyclerView
    private lateinit var adapter: ReviewRecyclerAdapter
    private lateinit var ratingBar: RatingBar
    private lateinit var ratingView: TextView
    private lateinit var addReviewButton: ImageButton
    //the product to be shown in this activity
    private lateinit var product: Product
    //the reviews assigned to this product
    private var revList = ArrayList<Review>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        castView()

        //get the product assigned to this page
        product = Warehouse.products[intent.getIntExtra("index", 0)]
        //sets the background color of the page
        when (product.category) {
            "Food" -> { mainLayout.setBackgroundColor(color(R.color.cat_food_background)) }
            "Beverage" -> { mainLayout.setBackgroundColor(color(R.color.cat_bev_background)) }
            "Pharmacy" -> { mainLayout.setBackgroundColor(color(R.color.cat_phar_background)) }
            "Military" -> { mainLayout.setBackgroundColor(color(R.color.cat_mil_background)) }
            "Tool" -> { mainLayout.setBackgroundColor(color(R.color.cat_tool_background)) }
        }
        //puts name, brand and description of product in the right text elements

        nameView.text = product.name
        brandView.text = product.brand
        descView.text = product.desc
        //view pager for multiple images
        viewPager.adapter = ViewPager2Adapter(this, product.imageList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        indicator.setViewPager(viewPager)

        // hide indicator if only 1 image
        if (product.imageList.size == 1) indicator.isVisible = false

        //format the price and show it
        var formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        formatter.currency = Currency.getInstance(Locale.US)
        priceView.text = formatter.format(product.price)

        infoView.text = "Category: " + product.category + "\n" +
                "Country of origin: " + product.country + "\n" +
                "Expiry date: " + product.expiry.toString() + "\n" +
                "Product mass: " + NumberFormat.getNumberInstance().format(product.mass) + "kg\n" +
                "Storage temperature: " + product.temp + "°C" + "\n" +
                product.stock + " left in stock"
        infoView.isVisible = false

        // more/less info button toggles visibility of additional info
        moreInfoButton.setOnClickListener {
            if (!infoView.isVisible) {
                infoView.isVisible = true
                // change text + arrowhead direction
                moreInfoButton.text = "SEE LESS"
                moreInfoButton.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_up_24, 0, 0, 0)
            }
            else {
                infoView.isVisible = false
                // change text + arrowhead direction
                moreInfoButton.text = "SEE MORE"
                moreInfoButton.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_keyboard_arrow_down_24, 0, 0, 0)
            }
        }

        revList = product.reviewList
        //for the recycler view containing reviews
        val layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
        reviewView.layoutManager = layoutManager
        adapter = ReviewRecyclerAdapter(revList)
        reviewView.adapter = adapter
        //sort button shows a dialog with sort options
        pReviewSort.setOnClickListener { view ->
            var builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
            var contentView: View = this.layoutInflater.inflate(R.layout.rev_sort_dialog, mainLayout, false)
            //5 radio buttons representing sort by date, name or rating, and reversing or not
            val sortBtnD: RadioButton = contentView.findViewById(R.id.rSortBtnD)
            val sortBtnN: RadioButton = contentView.findViewById(R.id.rSortBtnN)
            val sortBtnR: RadioButton = contentView.findViewById(R.id.rSortBtnR)
            val sortBtnUp: RadioButton = contentView.findViewById(R.id.rSortBtnUp)
            val sortBtnDown: RadioButton = contentView.findViewById(R.id.rSortBtnDown)
            //make the correct buttons checked at first
            when (Settings.reviewSortBy) {
                "Date" -> sortBtnD.isChecked = true
                "Name" -> sortBtnN.isChecked = true
                "Rating" -> sortBtnR.isChecked = true
            }
            if (Settings.reviewSortReverse) sortBtnDown.isChecked = true else sortBtnUp.isChecked = true
            //for each of the 5 radio buttons: uncheck the others that need to be unchecked,
            //update the setting and do the sort
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
            //build and show the dialog
            builder.setView(contentView)
            builder.setTitle("Sort By:")
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
        //initial sorting based on the settings
        sort(Settings.reviewSortBy, Settings.reviewSortReverse, adapter)
        //floating action button shows a dialog to create the review
        addReviewButton.setOnClickListener { view ->
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            var contentView: View = this.layoutInflater.inflate(R.layout.write_review_layout, mainLayout, false)
            val nameText: EditText = contentView.findViewById(R.id.nameText)
            val ratingBar: RatingBar = contentView.findViewById(R.id.ratingBar)
            val contentText: EditText = contentView.findViewById(R.id.reviewContentText)
            builder.setView(contentView)
            builder.setTitle("Write a Review")
            builder.setNegativeButton("Cancel", null)
            //when the post button is clicked, a review is added to the list
            //using the name, content and rating the user has entered
            builder.setPositiveButton("Post") { dialog, which ->
                revList.add(
                    Review(
                        nameText.text.toString(),
                        contentText.text.toString(),
                        (ratingBar.rating * 2).toInt(),
                        LocalDate.now()
                    )
                )
                adapter.notifyDataSetChanged()
                //the average rating of the product is updated
                updateRating()
                //the new review is placed on the right position based on sort options
                sort(Settings.reviewSortBy, Settings.reviewSortReverse, adapter)
            }
            builder.create().show()
        }
        //initial calculation of average rating
        updateRating()
    }
    //hide keyboard when user touches anywhere else (even though the keyboard cant be shown anyway)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        if(imm.isAcceptingText)
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)


        return true
    }
    //calculates the average rating of the product and updates the view
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
    //to fill a text view with less important information when the user wants to see them
    fun showAdditionalInfo() {

        infoView.text = "Country of origin: " + product.country + "\n" +
                "Expiry date: " + product.expiry.toString() + "\n" +
                "Product mass: " + NumberFormat.getNumberInstance().format(product.mass) + "kg\n" +
                "Storage temperature: " + product.temp + "°C" + "\n" +
                product.stock + " left in stock"

    }
    //removes the less important information from the text view
    fun hideAdditionalInfo() {

        infoView.text = null
        infoView.isVisible = false
    }
    //sort based on the options
    fun sort(sortingMode: String, reverse: Boolean, adapter: ReviewRecyclerAdapter) {
        when (sortingMode) {
            "Date" -> Collections.sort(adapter.revList, ReviewDateComparator())
            "Name" -> Collections.sort(adapter.revList, ReviewNameComparator())
            "Rating" -> Collections.sort(adapter.revList, ReviewRatingComparator())
        }
        if (reverse) adapter.revList.reverse()
        adapter.notifyDataSetChanged()
    }
    private fun castView() {
        //initialize the ui elements
        mainLayout = findViewById(R.id.main_product_layout)
        viewPager = findViewById(R.id.viewPager)
        nameView = findViewById(R.id.nameView)
        brandView = findViewById(R.id.brandView)
        descView = findViewById(R.id.descView)
        priceView = findViewById(R.id.priceView)
        infoView = findViewById(R.id.additionalInfoView)
        reviewView = findViewById(R.id.reviewRecycleView)
        ratingBar = findViewById(R.id.ratingBar2)
        pReviewSort = findViewById(R.id.p_review_sort)
        ratingView = findViewById(R.id.ratingView)
        indicator = findViewById(R.id.indicator)
        moreInfoButton = findViewById(R.id.moreInfoButton)
        addReviewButton = findViewById(R.id.addReviewButton)
    }
    //to get the color based on color id
    private fun color(id: Int): Int { return ContextCompat.getColor(this, id) }
}