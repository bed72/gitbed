package com.bed.gitbed.presentation.common.viewholder.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.bed.gitbed.databinding.ItemRepositoriesLoadMoreBinding

class RepositoriesLoadMoreViewHolder(
    itemLoadMore: ItemRepositoriesLoadMoreBinding,
    retry: () -> Unit
) :RecyclerView.ViewHolder(itemLoadMore.root) {

    private val binding = ItemRepositoriesLoadMoreBinding.bind(itemView)
    private val progressBarLoadMore = binding.progressLoadMore
    private val textTryAgainMessage = binding.textTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBarLoadMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): RepositoriesLoadMoreViewHolder {
            val itemBinding = ItemRepositoriesLoadMoreBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return RepositoriesLoadMoreViewHolder(itemBinding, retry)
        }
    }
}