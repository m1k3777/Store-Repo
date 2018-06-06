package com.mvillasenor.storeexample.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mvillasenor.storeexample.data.models.GithubUser
import com.mvillasenor.storerrepo.data.models.GithubRepo
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by Miguel Villaseñor on 6/5/18.
 */
class GitHubService {

    private val client: OkHttpClient = OkHttpClient.Builder()
            .build()
    private val gson: Gson = Gson()

    fun getUser(username: String) = Single.fromCallable {
        val request = Request.Builder()
                .url("https://api.github.com/users/" + username)
                .build()

        val response = client.newCall(request).execute()
        gson.fromJson(response.body()?.string(), GithubUser::class.java)
    }

    fun getRepos(username: String) = Single.fromCallable {
        val request = Request.Builder()
                .url("https://api.github.com/users/" + username + "/repos")
                .build()


        val reposType = object : TypeToken<List<GithubRepo>>() {}.type
        val response = client.newCall(request).execute()
        gson.fromJson<List<GithubRepo>>(response.body()?.string(), reposType)
    }


}