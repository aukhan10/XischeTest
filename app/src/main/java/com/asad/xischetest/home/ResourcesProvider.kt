package com.asad.xischetest.home

import android.content.Context
import androidx.annotation.StringRes
import com.asad.xischetest.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourcesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    fun getTotalCustomers(record: Int) = context.getString(R.string.home_total_records, record)
}