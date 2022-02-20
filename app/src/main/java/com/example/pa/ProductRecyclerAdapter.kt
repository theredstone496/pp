package com.example.pa

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.data.Product
import com.example.pa.data.Warehouse
import com.google.android.material.card.MaterialCardView
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
//recycler adapter for the main product view
class ProductRecyclerAdapter(var productList: ArrayList<Product>, var grid: Boolean) :
    RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View
        //uses different cards based on the chosen layout
        if (grid) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_layout_grid, parent, false)
        }
        else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_layout_row, parent, false)
        }
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productList[position])
    }

    override fun getItemCount() = productList.size

    // The class holding the product card
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cardView: MaterialCardView = itemView.findViewById(R.id.card_view)
        private var productImage: ImageView = itemView.findViewById(R.id.product_image)
        private var productName: TextView = itemView.findViewById(R.id.product_name)
        private var productPrice: TextView = itemView.findViewById(R.id.product_price)
        private var productRating: RatingBar = itemView.findViewById(R.id.product_rating)
        private var ratingCount: TextView = itemView.findViewById(R.id.ratingCount)

        fun bindItems(product : Product) {
            //sets the color based on product category
            when (product.category) {
                "Food" -> {
                    cardView.strokeColor = color(R.color.cat_food_stroke)
                    productPrice.setTextColor(color(R.color.cat_food_stroke))
                }
                "Beverage" -> {
                    cardView.strokeColor = color(R.color.cat_bev_stroke)
                    productPrice.setTextColor(color(R.color.cat_bev_stroke))
                }
                "Pharmacy" -> {
                    cardView.strokeColor = color(R.color.cat_phar_stroke)
                    productPrice.setTextColor(color(R.color.cat_phar_stroke))
                }

                "Military" -> {
                    cardView.strokeColor = color(R.color.cat_mil_stroke)
                    productPrice.setTextColor(color(R.color.cat_mil_stroke))
                }
            }
            //sets the product image on the card (the first image in the list)
            productImage.setImageResource(product.imageList[0])
            //sets the product name on the card
            productName.text = product.name
            //formats the price and puts it on the price view
            val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
            formatter.currency = Currency.getInstance(Locale.US)
            productPrice.text = formatter.format(product.price)
            //average rating and review count of the product is shown
            var rating = 0.0
            for (i in 0 until product.reviewList.size) {
                rating += product.reviewList[i].rating
            }
            if (product.reviewList.size != 0) {
                rating = (rating/product.reviewList.size)/2
            }
            productRating.rating = rating.toFloat()

            ratingCount.text = "("+product.reviewList.size+")"
            //when a card is clicked, bring the user to the second activity with more information
            itemView.setOnClickListener { view ->
                val intent = Intent(view.context, ProductActivity::class.java)
                //finds the position of the product in the list and puts it in the intent
                var actualposition = 0
                for (i: Int  in 0..Warehouse.products.size-1) {
                    if (Warehouse.products[i].name.equals(product.name)) actualposition = i
                }
                intent.putExtra("index", actualposition)
                view.context.startActivity(intent)
            }
        }
        //to get the color based on color id
        private fun color(id: Int): Int { return ContextCompat.getColor(cardView.context, id) }
    }
}
