package com.andrey.appviewusers.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andrey.appviewusers.model.UserResponse
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.retrofit.RetrofitService
import retrofit2.HttpException
import retrofit2.await
import java.io.IOException

/*class UsersPagingSource(private val userApi: RetrofitService): PagingSource<Int, Result>() {
    companion object{
        const val INITIAL_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: INITIAL_PAGE_INDEX
        return try {
            val response = userApi.getSomeData(page)
            LoadResult.Page(
                response.results, prevKey = if (page == INITIAL_PAGE_INDEX) null else page - 1,
                nextKey =  page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}*/

