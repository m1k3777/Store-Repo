package com.mvillasenor.storerrepo.data

import com.mvillasenor.storeexample.data.GitHubService
import com.mvillasenor.storeexample.data.models.GithubUser
import com.mvillasenor.storerrepo.data.models.GithubRepo
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import javax.inject.Inject

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
class GitHubRepoProvider @Inject constructor() : RepoProvider {

    private val gitHubService: GitHubService = GitHubService()

    override fun getUserRepo() = StoreBuilder.key<String, GithubUser>()
            .fetcher { gitHubService.getUser(it) }
            .open()

    override fun getReposRepo() = StoreBuilder.key<String, List<GithubRepo>>()
            .fetcher { gitHubService.getRepos(it) }
            .open()
}