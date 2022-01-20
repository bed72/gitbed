package com.bed.gitbed.presentation.common

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) =
    Snackbar.make(this, message, duration).show()

object Utils {
    const val PAGE_SIZE = 10
    const val TIMEOUT_SECONDS = 15L

    const val FLIPPER_CHILD_LOADING = 0
    const val FLIPPER_CHILD_CHARACTERS = 1
    const val FLIPPER_CHILD_ERROR = 2

    const val ID_USER = 30250307
    const val PATH_REPOSITORIES_INTERCEPTOR = "/user"
    const val PATH_COLLABORATORS_INTERCEPTOR = "/collaborators"
    const val LANGUAGE_MESSAGE_RESPONSE = "Opps esse veio sem linguagem :("
    const val DESCRIPTION_MESSAGE_RESPONSE = "Opps esse veio sem descrição :("
    const val AVATAR_URL = "https://avatars.githubusercontent.com/u/30250307?v=4"

    const val REPOSITORIES_PUBLIC = "false"
    const val REPOSITORIES_PRIVATE = "true"
}