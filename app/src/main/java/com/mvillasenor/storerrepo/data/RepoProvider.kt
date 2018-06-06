package com.mvillasenor.storerrepo.data

import com.mvillasenor.storeexample.data.models.GithubUser
import com.mvillasenor.storerrepo.data.models.GithubRepo
import com.nytimes.android.external.store3.base.impl.Store

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
interface RepoProvider {

    fun getUserRepo() : Store<GithubUser, String>
    fun getReposRepo() : Store<List<GithubRepo>, String>

}