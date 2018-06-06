package com.mvillasenor.storerrepo.data

import com.google.gson.reflect.TypeToken
import com.mvillasenor.storeexample.data.GitHubService
import com.mvillasenor.storeexample.data.models.GithubUser
import com.mvillasenor.storerrepo.Application
import com.mvillasenor.storerrepo.data.models.GithubRepo
import com.nytimes.android.external.fs3.FileSystemPersister
import com.nytimes.android.external.fs3.filesystem.FileSystemFactory
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import com.nytimes.android.external.store3.middleware.GsonParserFactory
import okio.BufferedSource
import javax.inject.Inject

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
class GitHubRepoProvider @Inject constructor(val application: Application) : RepoProvider {

    private val gitHubService: GitHubService = GitHubService()

    override fun getUserRepo() = StoreBuilder.parsedWithKey<String, BufferedSource, GithubUser>()
            .fetcher { gitHubService.getUser(it) }
            .persister(FileSystemPersister.create(FileSystemFactory.create(application.filesDir), { key -> "user-$key" }))
            .parser(GsonParserFactory.createSourceParser(GithubUser::class.java))
            .open()

    override fun getReposRepo() = StoreBuilder.parsedWithKey<String, BufferedSource, List<GithubRepo>>()
            .fetcher { gitHubService.getRepos(it) }
            .persister(FileSystemPersister.create(FileSystemFactory.create(application.filesDir), { key -> "repos-$key" }))
            .parser(GsonParserFactory.createSourceParser(object : TypeToken<List<GithubRepo>>() {}.type))
            .open()
}