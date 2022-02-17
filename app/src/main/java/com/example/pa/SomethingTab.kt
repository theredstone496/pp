package com.example.pa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pa.data.Review
import java.time.LocalDate

class SomethingTab : Fragment() {
    private var revList = ArrayList<Review>()
    private lateinit var reviewView2: RecyclerView
    private lateinit var adapter: ReviewRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        revList.add(Review("Michelle Obama", "I love spherical balls", 10, LocalDate.of(2022, 2, 16)))
        val view: View = inflater.inflate(R.layout.fragment_somethingtab, container, false)
        reviewView2 = view.findViewById(R.id.reviewRecycleView2)
        val layoutManager = LinearLayoutManager(this.context)
        reviewView2.layoutManager = layoutManager
        adapter = ReviewRecyclerAdapter(revList)
        reviewView2.adapter = adapter
        return view
    }
}