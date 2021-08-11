package com.andrey.appviewusers.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrey.appviewusers.databinding.UserBinding
import com.andrey.appviewusers.db.User

class UsersAdapter(
    private val clickListener: (User) -> Unit,
    private val paginationListener: () -> Unit
) :
    ListAdapter<User, UsersAdapter.MyViewHolder>(DiffCallback()) {

    private var prefetchCount = 3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bindingView = UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: User = currentList[position]

        holder.bind(item, clickListener)

        if (position == itemCount - prefetchCount) {
            paginationListener()
        }

    }

    class MyViewHolder(private val binding: UserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User, clickListener: (User) -> Unit) {
            binding.userName.text = item.getFullName()

            binding.root.setOnClickListener {
                clickListener(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

}
