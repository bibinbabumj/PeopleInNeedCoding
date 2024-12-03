package com.bibin.babu.software.developer.peopleinneedcoding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bibin.babu.software.developer.peopleinneedcoding.databinding.AdapterUserItemListViewBinding
import com.bibin.babu.software.developer.peopleinneedcoding.domain.model.UserModel

class UserRecyclerListAdapter : RecyclerView.Adapter<UserRecyclerListAdapter.UserItemViewHolder>() {
    private var userItemList: List<UserModel> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setUserItemList(userItemList: List<UserModel>) {
        this.userItemList = userItemList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder =
        UserItemViewHolder(
            AdapterUserItemListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        userItemList[position].let {
            holder.mBindData(it)
        }
    }

    override fun getItemViewType(position: Int): Int = position
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemCount(): Int = userItemList.size


    inner class UserItemViewHolder(private val binding: AdapterUserItemListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun mBindData(userModel: UserModel) {
            binding.usermodel = userModel
            binding.executePendingBindings()

        }
    }
}