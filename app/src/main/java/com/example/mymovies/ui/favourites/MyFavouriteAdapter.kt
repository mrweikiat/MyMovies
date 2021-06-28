package com.example.mymovies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mymovies.ui.favourites.FavouritesFragment

internal class MyFavouriteAdapter(
    private val context: FavouritesFragment,
    private val moviesData: ArrayList<Movie>
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private var image_URL = "https://image.tmdb.org/t/p/original"
    override fun getCount(): Int {
        return moviesData.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.favourite_movie_item, null)
        }

        imageView = convertView!!.findViewById(R.id.imageView)
        textView = convertView.findViewById(R.id.textView)
        var imagePath = image_URL + moviesData[position].poster

        // load img using glide
        Glide.with(imageView)
            .load(imagePath)
            .into(imageView)

        textView.text = moviesData[position].title


        return convertView
    }
}