package com.example.mymovies.ui.discover

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mymovies.R

internal class MainAdapter(
    private val context: DiscoverFragment,
    private val numbersInWords: ArrayList<String>,
    private val numberImage: IntArray
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    override fun getCount(): Int {
        return numbersInWords.size
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
        imageView.setImageResource(R.drawable.ic_notifications_black_24dp)
        textView.text = numbersInWords[position]
        return convertView
    }
}