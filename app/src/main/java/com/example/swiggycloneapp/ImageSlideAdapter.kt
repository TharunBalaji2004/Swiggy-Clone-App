package com.example.swiggycloneapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class ImageSlideAdapter(private val imageList: ArrayList<Int>, private val viewPager2: ViewPager2): RecyclerView.Adapter<ImageSlideAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val slideImage: ImageView = itemView.findViewById(R.id.slide_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_container, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.slideImage.setImageResource(imageList[position])
        if (position == imageList.size - 1){
            viewPager2.post(runnable)
        }
    }

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }
}