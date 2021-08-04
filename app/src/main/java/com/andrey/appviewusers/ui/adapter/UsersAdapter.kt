package com.andrey.appviewusers.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andrey.appviewusers.R
import com.andrey.appviewusers.model.Result

class UsersAdapter (val clickListener: (Result) -> Unit) :
    ListAdapter<Result, UsersAdapter.MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item: Result = currentList[position]

        holder.bind(item, clickListener)

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.user_name)

        fun bind (item: Result, clickListener: (Result) -> Unit)
        {
            nameTextView.text = item.name.getFullName()

            itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.login.uuid == newItem.login.uuid
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

}
