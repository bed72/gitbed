package com.bed.gitbed.presentation.common.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.bed.gitbed.presentation.common.viewholder.RepositoriesLoadMoreViewHolder

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