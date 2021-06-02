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
import com.ironclad.wedigittest.R
import com.ironclad.wedigittest.data.model.Cast
import com.ironclad.wedigittest.databinding.ListItemFeedBinding

class CastAdapter(private val context: Context) :
    ListAdapter<Cast, CastAdapter.CastViewHolder>(object : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }) {

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ListItemFeedBinding.inflate(LayoutInflater.from(context), parent, false)
        return CastViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        ListItemFeedBinding.bind(holder.itemView).apply {
            val people = getItem(position)

            textViewTitle.text = people.name
            textViewYear.text = people.character
            Glide.with(context)
                .load("${BuildConfig.IMAGE_BASE_URL}${people.profilePath}")
                .centerCrop()
                .placeholder(R.drawable.ic_profile)
                .into(imageViewPoster)
        }
    }
}