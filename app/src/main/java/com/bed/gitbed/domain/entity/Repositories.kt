package com.bed.gitbed.domain.entity

data class Repositories(
    val id: Number,
    val forks: Int,
    val stars: Int,
    val url: String,
    val name: String,
    val private: Boolean,
    val language: String,
    val description: String,
    val collaborators: List<Collaborators>
)
