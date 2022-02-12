package com.example.pa

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView

//to view more product details
class ProductActivity : AppCompatActivity() {
    private lateinit var productImage: ImageView
    private lateinit var nameView: TextView
    private lateinit var descView: TextView
    private lateinit var priceView: TextView
    private lateinit var infoView: TextView
    private lateinit var reviewView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        productImage = findViewById(R.id.productImage)
        nameView = findViewById(R.id.nameView)
        descView = findViewById(R.id.descView)
        priceView = findViewById(R.id.priceView)
        infoView = findViewById(R.id.additionalInfoView)
        reviewView = findViewById(R.id.reviewRecycleView)
        productImage.setImageDrawable(AppCompatResources.getDrawable(this, intent.getIntExtra("image", 0)))
        nameView.text = intent.getStringExtra("name")
        descView.text = intent.getStringExtra("desc")
        priceView.text = String.format("%.2f", intent.getDoubleExtra("price", 0.0))
    }
}