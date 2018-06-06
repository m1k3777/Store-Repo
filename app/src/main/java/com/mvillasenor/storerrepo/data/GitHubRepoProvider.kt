package com.mvillasenor.storerrepo.data

import com.google.gson.reflect.TypeToken
import com.mvillasenor.storeexample.data.GitHubService
import com.mvillasenor.storeexample.data.models.GithubUser
import com.mvillasenor.storerrepo.data.models.GithubRepo
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.GsonParserFactory
import okio.BufferedSource
import javax.inject.Inject

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
class GitHubRepoProvider @Inject constructor() : RepoProvider {

    private val gitHubService: GitHubService = GitHubService()

    override fun getUserRepo() = StoreBuilder.parsedWithKey<String, BufferedSource, GithubUser>()
            .fetcher { gitHubService.getUser(it) }
            .parser(GsonParserFactory.createSourceParser(GithubUser::class.java))
            .open()

    override fun getReposRepo() = StoreBuilder.parsedWithKey<String, BufferedSource, List<GithubRepo>>()
            .fetcher { gitHubService.getRepos(it) }
            .parser(GsonParserFactory.createSourceParser(object : TypeToken<List<GithubRepo>>() {}.type))
            .open()
}