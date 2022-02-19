package com.example.pa

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.comparators.ProductCatComparator
import com.example.pa.comparators.ProductNameComparator
import com.example.pa.comparators.ProductPriceComparator
import com.example.pa.data.Product
import com.example.pa.data.Review
import com.example.pa.data.Warehouse
import java.time.LocalDate
import android.widget.AdapterView
import com.example.pa.data.Settings


class ProductTab : Fragment() {
    private val productList = Warehouse.products
    private lateinit var searchET : EditText
    private lateinit var adapter: ProductRecyclerAdapter
    private lateinit var catBtnA: RadioButton
    private lateinit var catBtnF: RadioButton
    private lateinit var catBtnB: RadioButton
    private lateinit var catBtnP: RadioButton
    private lateinit var catBtnM: RadioButton
    private lateinit var catBtnT: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val view: View = inflater.inflate(R.layout.fragment_producttab, container, false)


        searchET = view.findViewById(R.id.searchET)
        val recyclerView = view.findViewById<RecyclerView>(R.id.product_recycler_view)
        val layoutManager = GridLayoutManager(context, 1)
        catBtnA = view.findViewById(R.id.catBtnA)
        catBtnF = view.findViewById(R.id.catBtnF)
        catBtnB = view.findViewById(R.id.catBtnB)
        catBtnP = view.findViewById(R.id.catBtnP)
        catBtnM = view.findViewById(R.id.catBtnM)
        catBtnT = view.findViewById(R.id.catBtnT)



        recyclerView.layoutManager = layoutManager

        adapter = ProductRecyclerAdapter(productList)
        recyclerView.adapter = adapter
        filterCat(Settings.viewedCategory, adapter)
        when (Settings.viewedCategory) {
            "All" -> catBtnA.isChecked = true
            "Food" -> catBtnF.isChecked = true
            "Beverage" -> catBtnB.isChecked = true
            "Pharmacy" -> catBtnP.isChecked = true
            "Military" -> catBtnM.isChecked = true
            "Tool" -> catBtnT.isChecked = true
        }

        searchET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                search(searchET.text.toString(), adapter)
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
        catBtnA.setOnClickListener {
            catBtnF.isChecked = false
            catBtnB.isChecked = false
            catBtnP.isChecked = false
            catBtnM.isChecked = false
            catBtnT.isChecked = false
            filterCat("All", adapter)
            Settings.viewedCategory = "All"
        }
        catBtnF.setOnClickListener {
            catBtnA.isChecked = false
            catBtnB.isChecked = false
            catBtnP.isChecked = false
            catBtnM.isChecked = false
            catBtnT.isChecked = false
            filterCat("Food", adapter)
            Settings.viewedCategory = "Food"
        }
        catBtnB.setOnClickListener {
            catBtnF.isChecked = false
            catBtnA.isChecked = false
            catBtnP.isChecked = false
            catBtnM.isChecked = false
            catBtnT.isChecked = false
            filterCat("Beverage", adapter)
            Settings.viewedCategory = "Beverage"
        }
        catBtnP.setOnClickListener {
            catBtnF.isChecked = false
            catBtnB.isChecked = false
            catBtnA.isChecked = false
            catBtnM.isChecked = false
            catBtnT.isChecked = false
            filterCat("Pharmacy", adapter)
            Settings.viewedCategory = "Pharmacy"
        }
        catBtnM.setOnClickListener {
            catBtnF.isChecked = false
            catBtnB.isChecked = false
            catBtnP.isChecked = false
            catBtnA.isChecked = false
            catBtnT.isChecked = false
            filterCat("Military", adapter)
            Settings.viewedCategory = "Military"
        }
        catBtnT.setOnClickListener {
            catBtnF.isChecked = false
            catBtnB.isChecked = false
            catBtnP.isChecked = false
            catBtnM.isChecked = false
            catBtnA.isChecked = false
            filterCat("Tool", adapter)
            Settings.viewedCategory = "Tool"
        }

        return view
    }


    fun search(search: String, adapter: ProductRecyclerAdapter) {
        val newProductList : ArrayList<Product> = ArrayList<Product>()
        for (product : Product in productList) {
            if (product.name.lowercase().contains(search.lowercase())) newProductList.add(product)
        }
        adapter.productList = newProductList
        adapter.notifyDataSetChanged()
    }
    fun filterCat(cat: String, adapter: ProductRecyclerAdapter) {
        if (cat != "All") {
            val newProductList: ArrayList<Product> = ArrayList<Product>()
            for (product: Product in productList) {
                if (product.category.lowercase() == cat.lowercase()) newProductList.add(product)
            }
            adapter.productList = newProductList
        }
        else adapter.productList = productList
        adapter.notifyDataSetChanged()
    }

}