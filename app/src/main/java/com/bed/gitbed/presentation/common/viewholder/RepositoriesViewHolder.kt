package com.bed.gitbed.presentation.common.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bed.gitbed.R
import com.bed.gitbed.databinding.ItemRepositoriesBinding
import com.bed.gitbed.domain.entity.Repositories

class RepositoriesViewHolder(
    itemRepositoriesBinding: ItemRepositoriesBinding
) : RecyclerView.ViewHolder(itemRepositoriesBinding.root) {

    private val name = itemRepositoriesBinding.nameRepository
    private val icon = itemRepositoriesBinding.iconRepository
    private val language = itemRepositoriesBinding.languageRepository
    private val description = itemRepositoriesBinding.descriptionRepository

    fun bind(repositories: Repositories) {
        name.text = repositories.name
        language.text = repositories.language
        description.text = repositories.description
        icon.setImageResource(typeIcon(repositories))
    }

    private fun typeIcon(repositories: Repositories) =
        if (repositories.private)
            R.drawable.ic_baseline_lock
        else R.drawable.ic_baseline_lock_open

    companion object {
        fun create(parent: ViewGroup): RepositoriesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemRepositoriesBinding.inflate(inflater, parent, false)

            return RepositoriesViewHolder(itemBinding)
        }
    }
}