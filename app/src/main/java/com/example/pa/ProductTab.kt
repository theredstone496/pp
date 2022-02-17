package com.example.pa

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.data.Product
import com.example.pa.data.Review
import com.example.pa.data.Warehouse
import java.time.LocalDate
import android.widget.Toast




class ProductTab : Fragment() {
    private val productList = Warehouse.products
    private lateinit var searchET : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val view: View = inflater.inflate(R.layout.fragment_producttab, container, false)

        searchET = view.findViewById(R.id.searchET)
        val recyclerView = view.findViewById<RecyclerView>(R.id.product_recycler_view)
        val layoutManager = GridLayoutManager(context, 1)

        recyclerView.layoutManager = layoutManager

        val adapter = ProductRecyclerAdapter(productList)
        recyclerView.adapter = adapter

        searchET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                search(searchET.text.toString(), adapter)
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        return view
    }

    fun search(search: String, adapter: ProductRecyclerAdapter) {
        val newProductList : ArrayList<Product> = ArrayList<Product>()
        for (product : Product in productList) {
            if (product.name.lowercase().contains(search.lowercase())) newProductList.add(product)
        }
        adapter.productList = newProductList
    }


}