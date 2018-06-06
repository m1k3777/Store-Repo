package com.mvillasenor.storerrepo.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.SearchView
import com.mvillasenor.storeexample.data.models.GithubUser
import com.mvillasenor.storerrepo.R
import com.mvillasenor.storerrepo.data.models.GithubRepo
import com.mvillasenor.storerrepo.ui.adapter.ReposAdapter
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var userProfileViewModel: UserProfileViewModel? = null
    private lateinit var reposAdapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        reposAdapter = ReposAdapter()
        repos.layoutManager = LinearLayoutManager(this)
        repos.adapter = reposAdapter

        userProfileViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UserProfileViewModel::class.java)

        userProfileViewModel?.getUser()?.observe(this, Observer { user -> loadUserInfo(user) })
        userProfileViewModel?.getRepos()?.observe(this, Observer { repos -> loadRepos(repos) })
        userProfileViewModel?.getError()?.observe(this, Observer { error -> showError(error) })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.isSubmitButtonEnabled = true
        searchView.setIconifiedByDefault(false)
        searchView.queryHint = getString(R.string.search)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            animator.displayedChild = 0
            userProfileViewModel?.loadUser(query)
            userProfileViewModel?.loadRepos(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun loadUserInfo(githubUser: GithubUser?) {
        if (githubUser?.login != null) {
            username.text = githubUser.login
            githubUser.avatar_url?.let {
                Picasso.get().load(githubUser.avatar_url).into(picture)
                animator.displayedChild = 1
            }
        } else {
            showError(getString(R.string.user_not_found))
        }
    }

    private fun loadRepos(githubRepos: List<GithubRepo>?) {
        if (githubRepos != null) {
            reposAdapter.updateRepos(githubRepos)
        }
    }

    private fun showError(errorMessage: String?) {
        errorMessage?.let {
            error.text = errorMessage
            userProfileViewModel?.acknowledgeError()
            animator.displayedChild = 2
        }
    }
}
