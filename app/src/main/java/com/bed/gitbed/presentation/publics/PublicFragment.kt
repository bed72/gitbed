package com.bed.gitbed.presentation.publics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Lifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.bed.gitbed.R
import com.bed.gitbed.databinding.FragmentPublicBinding
import com.bed.gitbed.presentation.common.adapter.RepositoriesAdapter
import com.bed.gitbed.presentation.common.adapter.RepositoriesLoadMoreAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PublicFragment : Fragment(R.layout.fragment_public) {

    private lateinit var binding: FragmentPublicBinding
    private val viewModel: PublicViewModel by viewModel()
    private lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPublicBinding.bind(view)

        initPublicAdapter()
        observeEvents()

        fetchRepositories()
    }

    private fun fetchRepositories() =
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchRepositories(REPOSITORIES_PUBLIC).collect { pagingData ->
                    repositoriesAdapter.submitData(pagingData)
                }
            }
        }


    private fun initPublicAdapter() {
        repositoriesAdapter = RepositoriesAdapter()

        with(binding.publics.recycler, {
            setHasFixedSize(true)
            adapter = repositoriesAdapter.withLoadStateFooter(
                footer = RepositoriesLoadMoreAdapter(
                    repositoriesAdapter::retry
                )
            )
        })
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            repositoriesAdapter.loadStateFlow.collectLatest { loadState ->
                binding.publics.flipper.displayedChild = when(loadState.refresh) {
                    is LoadState.Loading -> {
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        FLIPPER_CHILD_CHARACTERS
                    }
                    is LoadState.Error -> {
                        view?.snack(getString(R.string.text_error_repositories))
                        FLIPPER_CHILD_ERROR
                    }
                }
            }
        }
    }


    private fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_CHARACTERS = 1
        private const val FLIPPER_CHILD_ERROR = 2
        private const val REPOSITORIES_PUBLIC = "false"
    }
}