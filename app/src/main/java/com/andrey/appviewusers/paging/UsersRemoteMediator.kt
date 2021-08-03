package com.andrey.appviewusers.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.andrey.appviewusers.db.AppDatabase
import com.andrey.appviewusers.model.Result
import com.andrey.appviewusers.retrofit.RetrofitService
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

/*@ExperimentalPagingApi
class UsersRemoteMediator (
    private val service: RetrofitService,
    private val db: AppDatabase
) : RemoteMediator<Int, Result>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Result>): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = service.getSomeData(page)
            db.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    db.userDao().deleteResults()
                }
                val prevKey = if (page == 0) null else page - 1
                val nextkey =  page + 1

                db.userDao().insert(response.results)
            }
            return MediatorResult.Success(endOfPaginationReached = true)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

}

/**
 * get the last remote key inserted which had the data
 */
private suspend fun getLastRemoteKey(state: PagingState<Int, Re>): RemoteKeys? {
    return state.pages
        .lastOrNull { it.data.isNotEmpty() }
        ?.data?.lastOrNull()
        ?.let { doggo -> appDatabase.getRepoDao().remoteKeysDoggoId(doggo.id) }
}

/**
 * get the first remote key inserted which had the data
 */
private suspend fun getFirstRemoteKey(state: PagingState<Int, DoggoImageModel>): RemoteKeys? {
    return state.pages
        .firstOrNull() { it.data.isNotEmpty() }
        ?.data?.firstOrNull()
        ?.let { doggo -> appDatabase.getRepoDao().remoteKeysDoggoId(doggo.id) }
}

/**
 * get the closest remote key inserted which had the data
 */
private suspend fun getClosestRemoteKey(state: PagingState<Int, DoggoImageModel>): RemoteKeys? {
    return state.anchorPosition?.let { position ->
        state.closestItemToPosition(position)?.id?.let { repoId ->
            appDatabase.getRepoDao().remoteKeysDoggoId(repoId)
        }
    }
}
}*/
