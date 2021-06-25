package com.example.mymovies.ui.discover

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.mymovies.Movie
import com.example.mymovies.R

internal class MainAdapter(
    private val context: DiscoverFragment,
    private val movies: ArrayList<Movie>
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private var image_URL = "https://image.tmdb.org/t/p/original"

    override fun getCount(): Int {
        return movies.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup
    ): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.movie_item, null)
        }
        imageView = convertView!!.findViewById(R.id.imageView)
        textView = convertView.findViewById(R.id.textView)

        var imagePath = image_URL + movies[position].poster

        // load img using glide
        Glide.with(imageView)
            .load(imagePath)
            .into(imageView)

        textView.text = movies[position].title
        return convertView
    }
}