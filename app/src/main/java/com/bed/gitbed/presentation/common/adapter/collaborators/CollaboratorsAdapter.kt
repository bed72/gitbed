package com.bed.gitbed.presentation.common.adapter.collaborators

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bed.gitbed.domain.entity.Collaborators
import com.bed.gitbed.presentation.common.viewholder.collaborators.CollaboratorsViewHolder

class CollaboratorsAdapter : ListAdapter<Collaborators, CollaboratorsViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CollaboratorsViewHolder.create(parent)

    override fun onBindViewHolder(holder: CollaboratorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Collaborators>() {
            override fun areItemsTheSame(oldItem: Collaborators, newItem: Collaborators) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Collaborators,
                newItem: Collaborators
            ) = oldItem == newItem
        }
    }
}