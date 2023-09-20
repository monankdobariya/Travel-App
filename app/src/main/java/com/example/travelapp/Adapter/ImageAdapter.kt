package com.example.travelapp.Adapter


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.travelapp.Fragment.HomeFragment
import com.example.travelapp.ModelClass.ImageSliderModel
import com.example.travelapp.R

import java.util.*
import kotlin.collections.ArrayList

class ImageAdapter (var images:List<Int>): PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }

    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view =LayoutInflater.from(container.context).inflate(R.layout.image_item_file,container,false)
        var imageview=view.findViewById<ImageView>(R.id.imgview)
        imageview.setImageResource(images[position])
        container.addView(view)
        return view
    }
}