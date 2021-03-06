package com.example.mymovies.ui.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovies.Movie
import com.example.mymovies.R

class RecyclerFavouriteAdapter(private var movieList: ArrayList<Movie>, var onItemClicked:(Movie) -> Unit):
    RecyclerView.Adapter<RecyclerFavouriteAdapter.ViewHolder>() {

    private var imageURL = "https://image.tmdb.org/t/p/original"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_fragment_favourites_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = movieList[position].title
        var imagePath = imageURL + movieList[position].poster
        var image = holder.imageView
        // load img using glide
        Glide.with(image)
            .load(imagePath)
            .into(image)

        holder.itemView.setOnClickListener {
            onItemClicked(movieList[position])
        }

    }

    fun refreshList(list: ArrayList<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_view_recycler)
        val imageView: ImageView = view.findViewById(R.id.image_view_recycler)

    }
}

