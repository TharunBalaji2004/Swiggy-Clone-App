package com.example.swiggycloneapp.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swiggycloneapp.R
import com.example.swiggycloneapp.model.HoriFoodsDataClass
import com.example.swiggycloneapp.model.InstamartDataClass

class InstamartImageAdapter(private val instamartImageList: List<InstamartDataClass>): RecyclerView.Adapter<InstamartImageAdapter.InstamartImageHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InstamartImageAdapter.InstamartImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.instamart_card, parent, false)
        return InstamartImageHolder(view)
    }

    override fun onBindViewHolder(holder: InstamartImageAdapter.InstamartImageHolder, position: Int) {
        val data = instamartImageList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return instamartImageList.size
    }

    inner class InstamartImageHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivProduct: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val tvProductCompany: TextView = itemView.findViewById(R.id.product_company)
        private val tvProductName: TextView = itemView.findViewById(R.id.product_name)
        private val tvProductNameTamil: TextView = itemView.findViewById(R.id.product_name_tamil)
        private val tvProductWeight: TextView = itemView.findViewById(R.id.product_weight)
        private val tvOrgPrice: TextView = itemView.findViewById(R.id.tv_orgprice)
        private val tvDisPrice: TextView = itemView.findViewById(R.id.tv_disprice)

        fun bind(data: InstamartDataClass) {
            ivProduct.setImageResource(data.productImage)
            tvProductCompany.text = data.productCompany
            tvProductName.text = data.productName
            tvProductNameTamil.text = data.productNameTamil
            tvProductWeight.text = data.productWeight
            tvOrgPrice.text = "₹" + data.orgPrice
            tvOrgPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            tvDisPrice.text = "₹" + data.disPrice
        }
    }

}