package com.mvillasenor.storerrepo.di.data

import com.mvillasenor.storerrepo.data.GitHubRepoProvider
import com.mvillasenor.storerrepo.data.RepoProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindRepoProvider(gitHubRepoProvider: GitHubRepoProvider): RepoProvider

}