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
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.data.Product
import com.example.pa.data.Warehouse
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat

class ProductRecyclerAdapter(var productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItems(productList[position])
    }

    override fun getItemCount() = productList.size

    // The class holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView
        var productImage: ImageView
        var productName: TextView
        var productPrice: TextView
        var productRating: RatingBar

        init {
            cardView = itemView.findViewById(R.id.card_view)
            productImage = itemView.findViewById(R.id.product_image)
            productName = itemView.findViewById(R.id.product_name)
            productPrice = itemView.findViewById(R.id.product_price)
            productRating = itemView.findViewById(R.id.product_rating)

            itemView.setOnClickListener { view ->
                val pos = adapterPosition + 1
                Snackbar.make(view, "Click detected on item " + productName, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        fun bindItems(product : Product) {
            when (product.category) {
                "Food" -> {cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.cat_food_background))
                    productPrice.setTextColor(itemView.resources.getColor(R.color.cat_food_text))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_food_text))
                }
                "Beverage" -> {
                    cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.cat_bev_background))
                    productPrice.setTextColor(itemView.resources.getColor(R.color.cat_bev_text))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_bev_text))
                }
                "Pharmacy" -> {
                    cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.cat_phar_background))
                    productPrice.setTextColor(itemView.resources.getColor(R.color.cat_phar_text))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_phar_text))
                }
                "Military" -> {
                    cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.cat_mil_background))
                    productPrice.setTextColor(itemView.resources.getColor(R.color.cat_mil_text))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_mil_text))
                }
                else -> {

                }
            }
            productImage.setImageResource(product.imageList[0])
            productName.text = product.name
            productPrice.text = NumberFormat.getCurrencyInstance().format(product.price)
            var rating = 0.0
            for (i in 0..product.reviewList.size-1) {
                rating += product.reviewList[i].rating
            }
            if (product.reviewList.size != 0) {
                rating = rating/2/product.reviewList.size
                productRating.rating = rating.toFloat()
            }
            itemView.setOnClickListener { view ->
                val pos = adapterPosition + 1
                Snackbar.make(view, "Click detected on item " + product.name, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                val intent = Intent(view.context, ProductActivity::class.java)
                var actualposition = 0
                for (i: Int  in 0..Warehouse.products.size-1) {
                    if (Warehouse.products[i].name.equals(product.name)) actualposition = i
                }
                intent.putExtra("index", actualposition)
                view.context.startActivity(intent)
            }
        }
    }
}
