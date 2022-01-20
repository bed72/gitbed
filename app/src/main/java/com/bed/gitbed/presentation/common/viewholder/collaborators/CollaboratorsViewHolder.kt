package com.bed.gitbed.presentation.common.viewholder.collaborators

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bed.gitbed.databinding.ItemCollaboratorsBinding
import com.bed.gitbed.domain.entity.Collaborators
import com.bumptech.glide.Glide

class CollaboratorsViewHolder(
    itemCollaboratorsBinding: ItemCollaboratorsBinding
) : RecyclerView.ViewHolder(itemCollaboratorsBinding.root) {

    private val imageCollaborators = itemCollaboratorsBinding.imageCollaborators

    fun bind(collaborators: Collaborators) {
        Glide.with(itemView)
            .load(collaborators.avatarUrl)
            .into(imageCollaborators)
    }

    companion object {
        fun create(parent: ViewGroup): CollaboratorsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCollaboratorsBinding.inflate(inflater, parent, false)

            return CollaboratorsViewHolder(itemBinding)
        }
    }
}