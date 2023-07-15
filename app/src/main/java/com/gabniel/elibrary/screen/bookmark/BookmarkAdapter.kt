package com.gabniel.elibrary.screen.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabniel.elibrary.R
import com.gabniel.elibrary.database.entities.BookmarkEntity
import com.gabniel.elibrary.databinding.ItemBookmarkBinding
import com.squareup.picasso.Picasso

class BookmarkAdapter(
    private val listener: Listener,
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<BookmarkEntity>() {
        override fun areItemsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class BookmarkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBookmarkBinding.bind(view)
        fun bindData(item: BookmarkEntity) {
            binding.apply {
                Picasso.get().load(item.image).into(binding.imgPoster)
                binding.txtTitle.text = item.name
            }
            itemView.setOnClickListener {
                listener.onClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    interface Listener {
        fun onClickListener(data: BookmarkEntity)
    }

}