package com.evosouza.core.data

import androidx.paging.PagingSource
import com.evosouza.core.model.PhotoDomain

interface PopularRepository {
    fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain>
}