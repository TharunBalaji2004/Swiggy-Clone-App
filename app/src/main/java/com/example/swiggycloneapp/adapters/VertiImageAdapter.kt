package com.example.swiggycloneapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swiggycloneapp.R
import com.example.swiggycloneapp.model.VertiFoodsDataClass

class VertiImageAdapter(private val vertiImageList: List<VertiFoodsDataClass>): RecyclerView.Adapter<VertiImageAdapter.VertiImageHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VertiImageAdapter.VertiImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_verti_card, parent, false)
        return VertiImageHolder(view)
    }

    override fun onBindViewHolder(holder: VertiImageAdapter.VertiImageHolder, position: Int) {
        val data = vertiImageList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return vertiImageList.size
    }

    inner class VertiImageHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivFood: ImageView = itemView.findViewById(R.id.iv_food)
        private val tvHotelName: TextView = itemView.findViewById(R.id.tv_hotel_name)
        private val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        private val tvType: TextView = itemView.findViewById(R.id.tv_type)
        private val tvHotelLocation: TextView = itemView.findViewById(R.id.tv_hotel_location)
        private val ivOffer: ImageView= itemView.findViewById(R.id.iv_offer)
        fun bind(data: VertiFoodsDataClass) {
            ivFood.setImageResource(data.imageName)
            tvHotelName.text = data.hotelName
            tvRating.text = data.foodRating
            tvTime.text = data.deliveryTime
            tvType.text = data.foodType
            tvHotelLocation.text = data.hotelLocation
            ivOffer.setImageResource(data.offer)
        }
    }

}