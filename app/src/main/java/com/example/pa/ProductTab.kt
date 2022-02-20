package com.example.pa

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
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
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.util.*
import kotlin.collections.ArrayList
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener

import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout


class ProductTab : Fragment() {
    private val productList = Warehouse.products
    private lateinit var mainView: ViewGroup
    private lateinit var searchET: EditText
    private lateinit var adapter: ProductRecyclerAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var sortButton: ImageButton
    private lateinit var autoCompleteTextView: MaterialAutoCompleteTextView
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var rowLayoutButton: ImageButton
    private lateinit var gridLayoutButton: ImageButton
    private lateinit var itemCount: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_producttab, container, false)

        castView(view)

        val recyclerView = view.findViewById<RecyclerView>(R.id.product_recycler_view)
        refreshRecycler(recyclerView)

        filterCat()

        searchET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Settings.search = searchET.text.toString()
                search(Settings.search, adapter)
                filterCat()
                sort()
                refreshItemCount()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
        searchET.setText(Settings.search)
        search(Settings.search, adapter)
        filterCat()
        sort()
        refreshItemCount()

        rowLayoutButton.setOnClickListener { view ->
            if (Settings.grid) {
                Settings.grid = false
                refreshRecycler(recyclerView)
            }
        }

        gridLayoutButton.setOnClickListener { view ->
            if (!Settings.grid) {
                Settings.grid = true
                refreshRecycler(recyclerView)
            }
        }

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
                sort()
            }
            sortBtnN.setOnClickListener { view ->
                sortBtnC.isChecked = false
                sortBtnB.isChecked = false
                sortBtnP.isChecked = false
                Settings.sortBy = "Name"
                sort()
            }
            sortBtnB.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnC.isChecked = false
                sortBtnP.isChecked = false
                Settings.sortBy = "Brand"
                sort()
            }
            sortBtnP.setOnClickListener { view ->
                sortBtnN.isChecked = false
                sortBtnB.isChecked = false
                sortBtnC.isChecked = false
                Settings.sortBy = "Price"
                sort()
            }
            sortBtnUp.setOnClickListener { view ->
                sortBtnDown.isChecked = false
                Settings.sortReverse = false
                sort()
            }
            sortBtnDown.setOnClickListener { view ->
                sortBtnUp.isChecked = false
                Settings.sortReverse = true
                sort()
            }
            builder.setView(contentView)
            builder.setTitle("Sort By:")
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }

        if (Settings.viewedCategory != "All") autoCompleteTextView.setText(Settings.viewedCategory)
        else autoCompleteTextView.setText("Category")
        val categories = arrayOf("All", "Food", "Beverage", "Pharmacy", "Military", "Tool")
        val arrayAdapter = ArrayAdapter(context!!, R.layout.dropdown_layout, categories)
        autoCompleteTextView.setAdapter(arrayAdapter)
        (textInputLayout.getEditText() as AutoCompleteTextView).onItemClickListener =
            OnItemClickListener { adapterView, view, position, id ->
                Settings.viewedCategory = arrayAdapter.getItem(position)!!

                search(Settings.search, adapter)
                filterCat()
                sort()
                refreshItemCount()
            }
        return view
    }


    fun search(search: String, adapter: ProductRecyclerAdapter) {
        val newProductList : ArrayList<Product> = ArrayList<Product>()
        for (product : Product in productList) {
            if (product.name.lowercase().contains(search.lowercase())) newProductList.add(product)
        }
        adapter.productList = newProductList
        refreshItemCount()
        adapter.notifyDataSetChanged()
    }
    fun filterCat() {
        if (Settings.viewedCategory != "All") {
            val newProductList: ArrayList<Product> = ArrayList()
            for (product: Product in adapter.productList) {
                if (product.category.lowercase() == Settings.viewedCategory.lowercase()) newProductList.add(product)
            }
            adapter.productList = newProductList
        }
        adapter.notifyDataSetChanged()
    }
    fun sort() {
        val sortingMode = Settings.sortBy
        val reverse = Settings.sortReverse
        when (sortingMode) {
            "Category" -> Collections.sort(adapter.productList, ProductCatComparator())
            "Name" -> Collections.sort(adapter.productList, ProductNameComparator())
            "Brand" -> Collections.sort(adapter.productList, ProductBrandComparator())
            "Price" -> Collections.sort(adapter.productList, ProductPriceComparator())
        }
        if (reverse) adapter.productList.reverse()
        adapter.notifyDataSetChanged()
    }
    private fun castView(view: View) {
        mainView = view.findViewById(R.id.main_view)
        searchET = view.findViewById(R.id.searchET)
        sortButton = view.findViewById(R.id.sortButton)
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)
        textInputLayout = view.findViewById(R.id.textInputLayout)
        rowLayoutButton = view.findViewById(R.id.rowLayoutButton)
        gridLayoutButton = view.findViewById(R.id.gridLayoutButton)
        itemCount = view.findViewById(R.id.itemCount)
    }
    private fun refreshRecycler(recyclerView: RecyclerView) {

        layoutManager = GridLayoutManager(context, if (Settings.grid) 2 else 1)
        recyclerView.layoutManager = layoutManager

        adapter = ProductRecyclerAdapter(productList, Settings.grid)
        recyclerView.adapter = adapter

        search(Settings.search, adapter)
        filterCat()
        sort()
        refreshItemCount()
    }
    private fun refreshItemCount() {
        itemCount.text = "" + adapter.productList.size + " items found."
    }

}