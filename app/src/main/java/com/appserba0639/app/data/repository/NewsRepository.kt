package com.appserba0639.app.data.repository

import com.appserba0639.app.data.model.ActionState
import com.appserba0639.app.data.model.News
import com.appserba0639.app.data.remote.NewsService
import com.appserba0639.app.data.remote.RetrofitApi
import kotlinx.coroutines.channels.ActorScope
import retrofit2.await
import java.lang.Exception

class NewsRepository {
    private val newsService: NewsService by lazy { RetrofitApi.newsService() }

    suspend fun listNews() : ActionState<List<News>>{
        return try {
            val list = newsService.listNews().await()
            ActionState(list.data)
        }catch (e: Exception){
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun detailNews(url: String) : ActionState<News>{
        return try {
            val list = newsService.detailNews(url).await()
            ActionState(list.data.first())
        }catch (e: Exception){
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun searchNews(query: String) : ActionState<List<News>>{
        return try {
            val list = newsService.searchNews(query).await()
            ActionState(list.data)
        }catch (e: Exception){
            ActionState(message = e.message, isSuccess = false)
        }
    }

}