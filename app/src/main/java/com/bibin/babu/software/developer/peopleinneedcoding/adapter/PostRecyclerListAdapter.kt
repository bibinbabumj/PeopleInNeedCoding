package com.bibin.babu.software.developer.peopleinneedcoding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bibin.babu.software.developer.peopleinneedcoding.databinding.AdapterPostItemViewBinding
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.PostItem

class PostRecyclerListAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<PostRecyclerListAdapter.PostItemViewHolder>() {
    private var postItemList: List<PostItem> = emptyList()

    interface OnClickListener {
        fun onPostItemClick(postItem: PostItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPostItemList(postItemList: List<PostItem>) {
        this.postItemList = postItemList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostItemViewHolder =
        PostItemViewHolder(
            AdapterPostItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(
        holder: PostRecyclerListAdapter.PostItemViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            onClickListener.onPostItemClick(postItemList[position])
        }
        postItemList[position].let {
            holder.mBindData(it)
        }
    }

    override fun getItemViewType(position: Int): Int = position
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemCount(): Int = postItemList.size

    inner class PostItemViewHolder(private val binding: AdapterPostItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun mBindData(postItem: PostItem) {
            binding.postitems = postItem
            binding.executePendingBindings()

        }
    }
}