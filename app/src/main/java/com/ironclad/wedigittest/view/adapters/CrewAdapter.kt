package com.ironclad.wedigittest.view.adapters

import android.annotation.SuppressLint
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
import com.ironclad.wedigittest.data.model.Crew
import com.ironclad.wedigittest.databinding.ListItemCastCrewBinding

class CrewAdapter(private val context: Context) :
    ListAdapter<Crew, CrewAdapter.CrewViewHolder>(object : DiffUtil.ItemCallback<Crew>() {
        override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }) {

    inner class CrewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val binding = ListItemCastCrewBinding.inflate(LayoutInflater.from(context), parent, false)
        return CrewViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        ListItemCastCrewBinding.bind(holder.itemView).apply {
            val people = getItem(position)

            textViewTitle.text = people.name

            if (people.department == "Writing") {
                textViewYear.text = "${people.department} / ${people.job}"
            } else {
                textViewYear.text = people.job
            }

            if (people.profilePath != null) {
                Glide.with(context)
                    .load("${BuildConfig.IMAGE_BASE_URL}${people.profilePath}")
                    .centerCrop()
                    .placeholder(R.drawable.ic_profile)
                    .into(imageViewPoster)
            } else {
                Glide.with(context)
                    .load(R.drawable.ic_profile)
                    .centerCrop()
                    .placeholder(R.drawable.ic_profile)
                    .into(imageViewPoster)
            }
        }
    }
}