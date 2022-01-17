package com.bed.gitbed.presentation.privates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.bed.gitbed.R
import com.bed.gitbed.databinding.FragmentPrivateBinding
import com.bed.gitbed.databinding.FragmentPublicBinding
import com.bed.gitbed.presentation.common.adapter.RepositoriesAdapter
import com.bed.gitbed.presentation.common.adapter.RepositoriesLoadMoreAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import org.koin.androidx.viewmodel.ext.android.viewModel

class PrivateFragment : Fragment(R.layout.fragment_private) {

    private lateinit var binding: FragmentPrivateBinding
    private val viewModel: PrivateViewModel by viewModel()
    private lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPrivateBinding.bind(view)

        initPublicAdapter()
        observeEvents()

        fetchRepositories()
    }

    private fun fetchRepositories() =
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchRepositories(REPOSITORIES_PRIVATE).collect { pagingData ->
                    repositoriesAdapter.submitData(pagingData)
                }
            }
        }


    private fun initPublicAdapter() {
        repositoriesAdapter = RepositoriesAdapter()

        with(binding.privates.recycler, {
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
                binding.privates.flipper.displayedChild = when(loadState.refresh) {
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
        private const val REPOSITORIES_PRIVATE = "true"
    }
}