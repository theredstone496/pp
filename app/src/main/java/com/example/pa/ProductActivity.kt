package com.example.pa

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.data.Product
import com.example.pa.data.Review
import com.example.pa.data.Warehouse

//to view more product details
class ProductActivity : AppCompatActivity() {
    private lateinit var productImage: ImageView
    private lateinit var nameView: TextView
    private lateinit var descView: TextView
    private lateinit var priceView: TextView
    private lateinit var infoView: TextView
    private lateinit var reviewView: RecyclerView
    private lateinit var product: Product
    private var revList = ArrayList<Review>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        productImage = findViewById(R.id.productImage)
        nameView = findViewById(R.id.nameView)
        descView = findViewById(R.id.descView)
        priceView = findViewById(R.id.priceView)
        infoView = findViewById(R.id.additionalInfoView)
        reviewView = findViewById(R.id.reviewRecycleView)
        product = Warehouse.products[intent.getIntExtra("index", 0)]
        productImage.setImageDrawable(AppCompatResources.getDrawable(this, product.imageList[0]))
        nameView.text = product.name
        descView.text = product.desc
        priceView.text = String.format("$%.2f", product.price)
        revList = product.reviewList
        val layoutManager = LinearLayoutManager(this)
        reviewView.layoutManager = layoutManager
        val adapter = ReviewRecyclerAdapter(revList)
        reviewView.adapter = adapter
    }
}