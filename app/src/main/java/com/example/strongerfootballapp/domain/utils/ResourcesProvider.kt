package com.example.strongerfootballapp.domain.utils

import android.content.Context
import androidx.annotation.NonNull
import androidx.annotation.StringRes

class ResourcesProvider(
    private val context: Context
) {
    @NonNull
    fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
        return context.getString(resId, *formatArgs)
    }
}