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
//recycler adapter for the reviews
class ReviewRecyclerAdapter(val revList: ArrayList<Review>) :
    RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_layout,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ReviewRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItems(revList[position])
    }
    override fun getItemCount() = revList.size
    // The class holding the review
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //assigns the views to the right variables
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
        //puts the details of the review into the right element
        fun bindItems(rev : Review){
            reviewTitle.text = rev.name
            reviewRating.rating = rev.rating / 2.0f
            reviewContent.text = rev.content
            reviewDate.text = rev.date.toString()
        }
    }
}
