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
import com.ironclad.wedigittest.data.model.MovieListItem
import com.ironclad.wedigittest.databinding.ListItemFeedBinding

class PopularMovieAdapter(private val context: Context, private val listener: OnItemClickListener) :
    ListAdapter<MovieListItem, PopularMovieAdapter.PopularMovieViewHolder>(object :
        DiffUtil.ItemCallback<MovieListItem>() {
        override fun areItemsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }) {

    inner class PopularMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val movieItem = getItem(adapterPosition)
            if (adapterPosition != RecyclerView.NO_POSITION) listener.onItemClick(movieItem.id ?: 0)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding = ListItemFeedBinding.inflate(LayoutInflater.from(context), parent, false)

        return PopularMovieViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        ListItemFeedBinding.bind(holder.itemView).apply {
            val movieItem = getItem(position)

            textViewTitle.text = movieItem.title
            val year = movieItem.releaseDate?.substring(0, 4)
            textViewYear.text = year
            Glide.with(context)
                .load("${BuildConfig.IMAGE_BASE_URL}${movieItem.posterPath}")
                .centerCrop()
                .into(imageViewPoster)
        }
    }
}