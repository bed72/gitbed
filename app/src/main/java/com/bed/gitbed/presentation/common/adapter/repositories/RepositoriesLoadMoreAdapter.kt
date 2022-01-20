package com.bed.gitbed.presentation.common.adapter.repositories

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.bed.gitbed.presentation.common.viewholder.repositories.RepositoriesLoadMoreViewHolder

class RepositoriesLoadMoreAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RepositoriesLoadMoreViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = RepositoriesLoadMoreViewHolder.create(parent, retry)

    override fun onBindViewHolder(holder: RepositoriesLoadMoreViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}