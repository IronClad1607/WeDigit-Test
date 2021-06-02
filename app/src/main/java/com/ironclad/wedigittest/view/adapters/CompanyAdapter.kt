package com.ironclad.wedigittest.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ironclad.wedigittest.BuildConfig
import com.ironclad.wedigittest.data.model.ProductionCompany
import com.ironclad.wedigittest.databinding.ListItemProductionBinding

class CompanyAdapter(private val context: Context) :
    ListAdapter<ProductionCompany, CompanyAdapter.CompanyViewHolder>(object :
        DiffUtil.ItemCallback<ProductionCompany>() {
        override fun areItemsTheSame(
            oldItem: ProductionCompany,
            newItem: ProductionCompany
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductionCompany,
            newItem: ProductionCompany
        ): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }) {

    inner class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = ListItemProductionBinding.inflate(LayoutInflater.from(context), parent, false)
        return CompanyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        ListItemProductionBinding.bind(holder.itemView).apply {
            val company = getItem(position)

            textViewTitle.text = company.name
            Glide.with(context)
                .load("${BuildConfig.IMAGE_BASE_URL}${company.logoPath}")
                .into(imageViewPoster)
        }
    }
}