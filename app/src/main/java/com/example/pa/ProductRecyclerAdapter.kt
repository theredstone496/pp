package com.example.pa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class ProductRecyclerAdapter(val productList: ArrayList<Product>) :
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
        var productImage: ImageView
        var productName: TextView
        var productPrice: TextView

        init {
            productImage = itemView.findViewById(R.id.product_image)
            productName = itemView.findViewById(R.id.product_name)
            productPrice = itemView.findViewById(R.id.product_price)
            itemView.setOnClickListener { view ->
                val pos = adapterPosition + 1
                Snackbar.make(view, "Click detected on item $pos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        fun bindItems(product : Product) {
            productImage.setImageResource(product.image)
            productName.text = product.name
            productPrice.text = "$"+product.price
        }
    }
}
