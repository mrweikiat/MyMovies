package com.example.mymovies.ui.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovies.Movie
import com.example.mymovies.R
import com.google.android.material.snackbar.Snackbar

class RecyclerFavouriteAdapter(private val movieList: ArrayList<Movie>):
    RecyclerView.Adapter<RecyclerFavouriteAdapter.ViewHolder>() {

    private var image_URL = "https://image.tmdb.org/t/p/original"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_fragment_favourites_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = movieList[position].title
        var imagePath = image_URL + movieList[position].poster
        var image = holder.imageView
        // load img using glide
        Glide.with(image)
            .load(imagePath)
            .into(image)

        holder.itemView.setOnClickListener {
            Snackbar.make(it,"Hi",
                Snackbar.LENGTH_LONG).show()
        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view)  {
        val textView: TextView = view.findViewById(R.id.text_view_recycler)
        val imageView: ImageView = view.findViewById(R.id.image_view_recycler)

    }

}