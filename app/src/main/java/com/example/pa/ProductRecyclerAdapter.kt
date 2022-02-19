package com.example.pa

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.data.Product
import com.example.pa.data.Warehouse
import com.google.android.material.snackbar.Snackbar

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

        init {
            cardView = itemView.findViewById(R.id.card_view)
            productImage = itemView.findViewById(R.id.product_image)
            productName = itemView.findViewById(R.id.product_name)
            productPrice = itemView.findViewById(R.id.product_price)

            itemView.setOnClickListener { view ->
                val pos = adapterPosition + 1
                Snackbar.make(view, "Click detected on item " + productName, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        fun bindItems(product : Product) {
            when (product.category) {
                "Food" -> {cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.cat_food_background))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_food_text))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_food_text))
                }
                "Beverage" -> {
                    cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.cat_bev_background))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_bev_text))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_bev_text))
                }
                "Pharmacy" -> {
                    cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.cat_phar_background))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_phar_text))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_phar_text))
                }
                "Military" -> {
                    cardView.setCardBackgroundColor(itemView.resources.getColor(R.color.cat_mil_background))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_mil_text))
                    productName.setTextColor(itemView.resources.getColor(R.color.cat_mil_text))
                }
                else -> {

                }
            }
            productImage.setImageResource(product.imageList[0])
            productName.text = product.name
            productPrice.text = "$"+product.price
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
