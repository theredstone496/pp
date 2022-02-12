package com.example.pa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        productList.add(Product("FOOD", 2.00, "Iceceraem", 5, R.drawable.meltingface))
        val adapter = ProductRecyclerAdapter(productList)
        recyclerView.adapter = adapter

        return view
    }


}