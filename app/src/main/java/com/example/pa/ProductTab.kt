package com.example.pa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.data.Review
import java.time.LocalDate

class ProductTab : Fragment() {
    private val productList = ArrayList<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val view: View = inflater.inflate(R.layout.fragment_producttab, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.product_recycler_view)
        val layoutManager = GridLayoutManager(context, 1)

        recyclerView.layoutManager = layoutManager
        val imageList = ArrayList<Int>()
        val reviewList = ArrayList<Review>()
        imageList.add(R.drawable.meltingface)
        reviewList.add(Review("Human", "Tastes like your mom", 9, LocalDate.of(2022, 2, 19)))
        productList.add(Product("FOOD", 2.00, "Iceceraem", "Snowman", "creamy", "Russia", LocalDate.of(2099, 9, 20), 2.0, 5, imageList, reviewList))
        val adapter = ProductRecyclerAdapter(productList)
        recyclerView.adapter = adapter

        return view
    }


}