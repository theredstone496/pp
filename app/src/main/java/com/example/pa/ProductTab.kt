package com.example.pa

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.comparators.ProductCatComparator
import com.example.pa.comparators.ProductNameComparator
import com.example.pa.comparators.ProductPriceComparator
import com.example.pa.data.Product
import com.example.pa.data.Warehouse
import com.example.pa.comparators.ProductBrandComparator
import com.example.pa.data.Settings
import java.util.*
import kotlin.collections.ArrayList


class ProductTab : Fragment() {
    private val productList = Warehouse.products
    private lateinit var mainView: ViewGroup
    private lateinit var searchET : EditText
    private lateinit var adapter: ProductRecyclerAdapter
    private lateinit var catBtnA: RadioButton
    private lateinit var catBtnF: RadioButton
    private lateinit var catBtnB: RadioButton
    private lateinit var catBtnP: RadioButton
    private lateinit var catBtnM: RadioButton
    private lateinit var catBtnT: RadioButton
    private lateinit var sortButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val view: View = inflater.inflate(R.layout.fragment_producttab, container, false)

        mainView = view.findViewById(R.id.main_view)
        searchET = view.findViewById(R.id.searchET)
        val recyclerView = view.findViewById<RecyclerView>(R.id.product_recycler_view)
        val layoutManager = GridLayoutManager(context, 1)
        catBtnA = view.findViewById(R.id.catBtnA)
        catBtnF = view.findViewById(R.id.catBtnF)
        catBtnB = view.findViewById(R.id.catBtnB)
        catBtnP = view.findViewById(R.id.catBtnP)
        catBtnM = view.findViewById(R.id.catBtnM)
        catBtnT = view.findViewById(R.id.catBtnT)
        sortButton = view.findViewById(R.id.sortButton)

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
        sortButton.setOnClickListener { view ->
            var builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            var contentView: View = this.layoutInflater.inflate(R.layout.sort_dialog, mainView, false)
            val sortBtnC: RadioButton = contentView.findViewById(R.id.rSortBtnD)
            val sortBtnN: RadioButton = contentView.findViewById(R.id.rSortBtnN)
            val sortBtnB: RadioButton = contentView.findViewById(R.id.rSortBtnR)
            val sortBtnP: RadioButton = contentView.findViewById(R.id.sortBtnP)
            val sortBtnUp: RadioButton = contentView.findViewById(R.id.rSortBtnUp)
            val sortBtnDown: RadioButton = contentView.findViewById(R.id.rSortBtnDown)
            when (Settings.sortBy) {
                "Category" -> sortBtnC.isChecked = true
                "Name" -> sortBtnN.isChecked = true
                "Brand" -> sortBtnB.isChecked = true
                "Price" -> sortBtnP.isChecked = true
            }
            if (Settings.sortReverse) sortBtnDown.isChecked = true else sortBtnUp.isChecked = true
            sortBtnC.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnB.isChecked = false
                sortBtnP.isChecked = false
                Settings.sortBy = "Category"
                sort(Settings.sortBy, Settings.sortReverse, adapter)
            }
            sortBtnN.setOnClickListener { view ->
                sortBtnC.isChecked = false
                sortBtnB.isChecked = false
                sortBtnP.isChecked = false
                Settings.sortBy = "Name"
                sort(Settings.sortBy, Settings.sortReverse, adapter)
            }
            sortBtnB.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnC.isChecked = false
                sortBtnP.isChecked = false
                Settings.sortBy = "Brand"
                sort(Settings.sortBy, Settings.sortReverse, adapter)
            }
            sortBtnP.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnB.isChecked = false
                sortBtnC.isChecked = false
                Settings.sortBy = "Price"
                sort(Settings.sortBy, Settings.sortReverse, adapter)
            }
            sortBtnUp.setOnClickListener { view ->
                sortBtnDown.isChecked = false
                Settings.sortReverse = false
                sort(Settings.sortBy, Settings.sortReverse, adapter)
            }
            sortBtnDown.setOnClickListener { view ->
                sortBtnUp.isChecked = false
                Settings.sortReverse = true
                sort(Settings.sortBy, Settings.sortReverse, adapter)
            }
            builder.setView(contentView)
            builder.setTitle("Sort By:")
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
        catBtnA.setOnClickListener {
            catBtnF.isChecked = false
            catBtnB.isChecked = false
            catBtnP.isChecked = false
            catBtnM.isChecked = false
            catBtnT.isChecked = false
            filterCat("All", adapter)
            Settings.viewedCategory = "All"
            sort(Settings.sortBy, Settings.sortReverse, adapter)
        }
        catBtnF.setOnClickListener {
            catBtnA.isChecked = false
            catBtnB.isChecked = false
            catBtnP.isChecked = false
            catBtnM.isChecked = false
            catBtnT.isChecked = false
            filterCat("Food", adapter)
            Settings.viewedCategory = "Food"
            sort(Settings.sortBy, Settings.sortReverse, adapter)
        }
        catBtnB.setOnClickListener {
            catBtnF.isChecked = false
            catBtnA.isChecked = false
            catBtnP.isChecked = false
            catBtnM.isChecked = false
            catBtnT.isChecked = false
            filterCat("Beverage", adapter)
            Settings.viewedCategory = "Beverage"
            sort(Settings.sortBy, Settings.sortReverse, adapter)
        }
        catBtnP.setOnClickListener {
            catBtnF.isChecked = false
            catBtnB.isChecked = false
            catBtnA.isChecked = false
            catBtnM.isChecked = false
            catBtnT.isChecked = false
            filterCat("Pharmacy", adapter)
            Settings.viewedCategory = "Pharmacy"
            sort(Settings.sortBy, Settings.sortReverse, adapter)
        }
        catBtnM.setOnClickListener {
            catBtnF.isChecked = false
            catBtnB.isChecked = false
            catBtnP.isChecked = false
            catBtnA.isChecked = false
            catBtnT.isChecked = false
            filterCat("Military", adapter)
            Settings.viewedCategory = "Military"
            sort(Settings.sortBy, Settings.sortReverse, adapter)
        }
        catBtnT.setOnClickListener {
            catBtnF.isChecked = false
            catBtnB.isChecked = false
            catBtnP.isChecked = false
            catBtnM.isChecked = false
            catBtnA.isChecked = false
            filterCat("Tool", adapter)
            Settings.viewedCategory = "Tool"
            sort(Settings.sortBy, Settings.sortReverse, adapter)
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
    fun sort(sortingMode: String, reverse: Boolean, adapter: ProductRecyclerAdapter) {
        when (sortingMode) {
            "Category" -> Collections.sort(adapter.productList, ProductCatComparator())
            "Name" -> Collections.sort(adapter.productList, ProductNameComparator())
            "Brand" -> Collections.sort(adapter.productList, ProductBrandComparator())
            "Price" -> Collections.sort(adapter.productList, ProductPriceComparator())
        }
        if (reverse) adapter.productList.reverse()
        adapter.notifyDataSetChanged()
    }

}