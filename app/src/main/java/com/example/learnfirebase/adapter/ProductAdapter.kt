package com.example.learnfirebase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learnfirebase.ProductModel
import com.example.learnfirebase.R
import kotlinx.android.synthetic.main.emp_list_item.view.*


class ProductAdapter(
    private val ds: ArrayList<ProductModel>,
    private val context: Context
):RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.imgSanPham)
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.emp_list_item,parent,false)
        return ProductAdapter.ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.itemView.apply {
            tvTenSp.text = ds[position].tenSP
            tvLoaiSp.text = ds[position].loaiSP
            tvGiaSp.text = ds[position].giaSP
            Glide.with(context).load(ds[position].imgSP).into(holder.image)

        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }




}