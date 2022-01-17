package com.bed.gitbed.presentation.common.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bed.gitbed.domain.entity.Repositories
import com.bed.gitbed.presentation.common.viewholder.RepositoriesViewHolder

class RepositoriesAdapter : PagingDataAdapter<Repositories, RepositoriesViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepositoriesViewHolder.create(parent)

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Repositories>() {
            override fun areItemsTheSame(oldItem: Repositories, newItem: Repositories) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repositories, newItem: Repositories) =
                oldItem == newItem
        }
    }
}