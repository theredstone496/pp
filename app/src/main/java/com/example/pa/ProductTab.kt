package com.example.pa

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
import android.widget.AdapterView.OnItemClickListener

import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout


class ProductTab : Fragment() {
    //the full list of products to be shown
    private val productList = Warehouse.products
    //ui elements
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
        //initialize the recycler view
        val recyclerView = view.findViewById<RecyclerView>(R.id.product_recycler_view)
        //whenever the search text is changed, update the view
        searchET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Settings.search = searchET.text.toString()
                //refresh the recycler view when a search is made
                refreshRecycler(recyclerView)
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
        //initially sets the search based on the setting
        searchET.setText(Settings.search)
        //put in the correct cards into the recycler view
        refreshRecycler(recyclerView)
        //changes the recycler to a linear layout
        rowLayoutButton.setOnClickListener { view ->
            if (Settings.grid) {
                Settings.grid = false
                refreshRecycler(recyclerView)
            }
        }
        //changes the recycler to a grid layout
        gridLayoutButton.setOnClickListener { view ->
            if (!Settings.grid) {
                Settings.grid = true
                refreshRecycler(recyclerView)
            }
        }
        //sort button shows a dialog used to set sort options
        sortButton.setOnClickListener { view ->
            var builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            var contentView: View = this.layoutInflater.inflate(R.layout.sort_dialog, mainView, false)
            val sortBtnC: RadioButton = contentView.findViewById(R.id.rSortBtnD)
            val sortBtnN: RadioButton = contentView.findViewById(R.id.rSortBtnN)
            val sortBtnB: RadioButton = contentView.findViewById(R.id.rSortBtnR)
            val sortBtnP: RadioButton = contentView.findViewById(R.id.rSortBtnP)
            val sortBtnUp: RadioButton = contentView.findViewById(R.id.rSortBtnUp)
            val sortBtnDown: RadioButton = contentView.findViewById(R.id.rSortBtnDown)
            //checks the correct buttons based on the setting
            when (Settings.sortBy) {
                "Category" -> sortBtnC.isChecked = true
                "Name" -> sortBtnN.isChecked = true
                "Brand" -> sortBtnB.isChecked = true
                "Price" -> sortBtnP.isChecked = true
            }
            if (Settings.sortReverse) sortBtnDown.isChecked = true else sortBtnUp.isChecked = true
            //for each button: uncheck the other buttons of the same type, changes the setting and then sorts
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
            //create and show the dialog
            builder.setView(contentView)
            builder.setTitle("Sort By:")
            builder.setPositiveButton("Ok", null)
            builder.create().show()
        }
        //set the initial text and the options of the auto complete text
        if (Settings.viewedCategory != "All") autoCompleteTextView.setText(Settings.viewedCategory)
        else autoCompleteTextView.setText("Category")
        val categories = arrayOf("All", "Food", "Beverage", "Pharmacy", "Military", "Tool")
        val arrayAdapter = ArrayAdapter(context!!, R.layout.dropdown_layout, categories)
        autoCompleteTextView.setAdapter(arrayAdapter)
        //when a new category is selected
        (textInputLayout.getEditText() as AutoCompleteTextView).onItemClickListener =
            OnItemClickListener { adapterView, view, position, id ->
                //update the setting
                Settings.viewedCategory = arrayAdapter.getItem(position)!!
                //show only products of the selected category
                refreshRecycler(recyclerView)
            }
        return view
    }

    //the search function
    fun search(search: String, adapter: ProductRecyclerAdapter) {
        //create a new product list
        val newProductList : ArrayList<Product> = ArrayList<Product>()
        //only add the products containing the search (ignore case) to the new list
        for (product : Product in productList) {
            if (product.name.lowercase().contains(search.lowercase())) newProductList.add(product)
        }
        //puts the new product list into the recycler view
        adapter.productList = newProductList
        adapter.notifyDataSetChanged()
    }
    //function to only show a certain category
    fun filterCat() {
        if (Settings.viewedCategory != "All") {
            //create a new product list (only if a certain category is selected and not all)
            val newProductList: ArrayList<Product> = ArrayList()
            //adds products of the specific category to the new list
            for (product: Product in adapter.productList) {
                if (product.category.lowercase() == Settings.viewedCategory.lowercase()) newProductList.add(product)
            }
            //puts the new list into the recycler view
            adapter.productList = newProductList
        }
        adapter.notifyDataSetChanged()
    }
    //function to sort based on the settings
    fun sort() {
        val sortingMode = Settings.sortBy
        val reverse = Settings.sortReverse
        //based on the sorting mode, sort the recycler view's list with the right comparator
        when (sortingMode) {
            "Category" -> Collections.sort(adapter.productList, ProductCatComparator())
            "Name" -> Collections.sort(adapter.productList, ProductNameComparator())
            "Brand" -> Collections.sort(adapter.productList, ProductBrandComparator())
            "Price" -> Collections.sort(adapter.productList, ProductPriceComparator())
        }
        //if the option to reverse is enabled reverse the list
        if (reverse) adapter.productList.reverse()
        adapter.notifyDataSetChanged()
    }
    //assign views to the right variables
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
    //refreshing the recycler view in 5 steps
    private fun refreshRecycler(recyclerView: RecyclerView) {
        //1. sets the layout of the recycler view (grid or linear) based on the setting
        layoutManager = GridLayoutManager(context, if (Settings.grid) 2 else 1)
        recyclerView.layoutManager = layoutManager
        adapter = ProductRecyclerAdapter(productList, Settings.grid)
        recyclerView.adapter = adapter
        //2. does the search based on the search text
        search(Settings.search, adapter)
        //3. further filters the products based on which category is selected
        filterCat()
        //4. sort the items
        sort()
        //5. update how many items are shown in the recycler view
        refreshItemCount()
    }
    //sets the text of a text view based on how many items are shown in the recycler view
    private fun refreshItemCount() {
        itemCount.text = "" + adapter.productList.size + " items found."
    }

}