package com.mvillasenor.storerrepo.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mvillasenor.storeexample.data.models.GithubUser
import com.mvillasenor.storerrepo.data.RepoProvider
import com.mvillasenor.storerrepo.data.models.GithubRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
class UserProfileViewModel @Inject constructor(private val repoProvider: RepoProvider) : ViewModel() {

    private val userLiveData = MutableLiveData<GithubUser>()
    private val reposLiveData = MutableLiveData<List<GithubRepo>>()
    private val errorLiveData = MutableLiveData<String>()

    fun getUser(): LiveData<GithubUser> {
        if (userLiveData.value == null) {
            loadUser("octocat")
        }
        return userLiveData
    }

    fun getRepos(): LiveData<List<GithubRepo>> {
        if (reposLiveData.value == null) {
            loadRepos("octocat")
        }
        return reposLiveData
    }

    fun getError() = errorLiveData

    fun loadUser(username: String) {
        repoProvider.getUserRepo().get(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userLiveData::postValue, this::sendError)
    }

    fun loadRepos(username: String) {
        repoProvider.getReposRepo().get(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reposLiveData::postValue, this::sendError)
    }

    fun acknowledgeError() {
        errorLiveData.postValue(null)
    }

    private fun sendError(error: Throwable) {
        errorLiveData.postValue(error.localizedMessage)
        Timber.e(error, "Error Loading user")
    }

}