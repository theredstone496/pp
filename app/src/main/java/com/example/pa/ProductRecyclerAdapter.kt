package com.example.pa

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.data.Product
import com.example.pa.data.Warehouse
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductRecyclerAdapter(var productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productList[position])
    }

    override fun getItemCount() = productList.size

    // The class holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cardView: MaterialCardView = itemView.findViewById(R.id.card_view)
        private var productImage: ImageView = itemView.findViewById(R.id.product_image)
        private var productName: TextView = itemView.findViewById(R.id.product_name)
        private var productPrice: TextView = itemView.findViewById(R.id.product_price)
        private var productRating: RatingBar = itemView.findViewById(R.id.product_rating)
        private var ratingCount: TextView = itemView.findViewById(R.id.ratingCount)

        fun bindItems(product : Product) {
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
            productImage.setImageResource(product.imageList[0])

            productName.text = product.name

            val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
            formatter.currency = Currency.getInstance(Locale.US)
            productPrice.text = formatter.format(product.price)

            var rating = 0.0
            for (i in 0 until product.reviewList.size) {
                rating += product.reviewList[i].rating
            }
            if (product.reviewList.size != 0) {
                rating = (rating/product.reviewList.size)/2
            }
            productRating.rating = rating.toFloat()

            ratingCount.text = "("+product.reviewList.size+")"

            itemView.setOnClickListener { view ->
                val intent = Intent(view.context, ProductActivity::class.java)
                var actualposition = 0
                for (i: Int  in 0..Warehouse.products.size-1) {
                    if (Warehouse.products[i].name.equals(product.name)) actualposition = i
                }
                intent.putExtra("index", actualposition)
                view.context.startActivity(intent)
            }
        }
        private fun color(id: Int): Int { return ContextCompat.getColor(cardView.context, id) }
    }
}
