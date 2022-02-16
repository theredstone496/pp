package com.example.pa
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.widget.ImageView
import android.view.LayoutInflater
import android.widget.RatingBar
import com.example.pa.data.Review
import com.google.android.material.snackbar.Snackbar
class ReviewRecyclerAdapter(val chpsList: ArrayList<Review>) :
    RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_layout,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ReviewRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItems(chpsList[position])
    }
    override fun getItemCount() = chpsList.size
    // The class holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var reviewTitle: TextView
        var reviewRating: RatingBar
        var reviewContent: TextView
        var reviewDate: TextView
        init {
            reviewTitle = itemView.findViewById(R.id.reviewTitle)
            reviewRating = itemView.findViewById(R.id.reviewRating)
            reviewContent = itemView.findViewById(R.id.reviewContent)
            reviewDate = itemView.findViewById(R.id.reviewDate)

        }
        fun bindItems(rev : Review){
            reviewTitle.text = rev.name
            reviewRating.rating = rev.rating / 2.0f
            reviewContent.text = rev.content
            reviewDate.text = rev.date.toString()
        }
    }
}
