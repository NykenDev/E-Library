package com.gabniel.elibrary.screen.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabniel.elibrary.R
import com.gabniel.elibrary.database.entities.EbookEntity
import com.gabniel.elibrary.databinding.ItemBookBinding
import com.squareup.picasso.Picasso


class HomeAdapter(
    private val listener: Listener,
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<EbookEntity>() {
        override fun areItemsTheSame(oldItem: EbookEntity, newItem: EbookEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: EbookEntity, newItem: EbookEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)


    inner class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBookBinding.bind(view)
        fun bindData(item: EbookEntity) {
            binding.apply {
                Picasso.get().load(item.image).into(binding.imgPoster)
            }
            itemView.setOnClickListener {
                listener.onClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    interface Listener {
        fun onClickListener(data: EbookEntity)
    }
}