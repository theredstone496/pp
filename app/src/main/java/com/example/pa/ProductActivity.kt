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
import com.example.pa.data.Product
import com.example.pa.data.Review
import com.example.pa.data.Warehouse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.NumberFormat
import java.time.LocalDate

//to view more product details
class ProductActivity : AppCompatActivity() {
    private lateinit var mainLayout: ViewGroup
    private lateinit var productImage: ImageView
    private lateinit var catView: TextView
    private lateinit var nameView: TextView
    private lateinit var brandView: TextView
    private lateinit var descView: TextView
    private lateinit var priceView: TextView
    private lateinit var switch: Switch
    private lateinit var infoView: TextView
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
        productImage = findViewById(R.id.productImage)
        catView = findViewById(R.id.catView)
        nameView = findViewById(R.id.nameView)
        brandView = findViewById(R.id.brandView)
        descView = findViewById(R.id.descView)
        priceView = findViewById(R.id.priceView)
        switch = findViewById(R.id.additionalSwitch)
        infoView = findViewById(R.id.additionalInfoView)
        reviewView = findViewById(R.id.reviewRecycleView)
        ratingBar = findViewById(R.id.ratingBar2)
        ratingView = findViewById(R.id.ratingView)
        fab = findViewById(R.id.reviewfab)
        product = Warehouse.products[intent.getIntExtra("index", 0)]
        productImage.setImageDrawable(AppCompatResources.getDrawable(this, product.imageList[0]))
        catView.text = getString(R.string.cat, product.category)
        nameView.text = product.name
        brandView.text = product.brand
        descView.text = product.desc
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
}