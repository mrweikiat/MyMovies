package com.example.mymovies.ui.MovieDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mymovies.Genre
import com.example.mymovies.Movie
import com.example.mymovies.R
import com.example.mymovies.ui.discover.DiscoverFragment

internal class MovieTagAdapter(
    private val context: MovieDetailsFragment,
    private val movie_tags: ArrayList<Genre>
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var textView: TextView

    override fun getCount(): Int {
        return movie_tags.size
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
            convertView = layoutInflater!!.inflate(R.layout.movie_tag, null)
        }

        textView = convertView!!.findViewById(R.id.movie_tag)

        textView.text = movie_tags[position].tag_name
        return convertView
    }
}