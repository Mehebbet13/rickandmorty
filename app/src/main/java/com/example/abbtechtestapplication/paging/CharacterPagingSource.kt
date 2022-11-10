package com.example.abbtechtestapplication.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.abbtechtestapplication.api.RickAndMortyApi
import com.example.abbtechtestapplication.models.Character


class CharacterPagingSource(private val characterAPI: RickAndMortyApi,private val name:String) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val position = params.key ?: 1
            val response = characterAPI.filterCharacter(name)

            return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.info.pages) null else position + 1
            )
        } catch (e: Exception) {
            Log.e("ayt",e.toString())
            LoadResult.Error(e)

        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}