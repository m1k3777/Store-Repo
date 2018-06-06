package com.mvillasenor.storerrepo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mvillasenor.storerrepo.R
import com.mvillasenor.storerrepo.data.models.GithubRepo

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
class ReposAdapter : RecyclerView.Adapter<ReposViewHolder>() {

    val repos = mutableListOf<GithubRepo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ReposViewHolder(view)
    }

    override fun getItemCount() =
            repos.size


    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    fun updateRepos(newRepos: List<GithubRepo>) {
        repos.clear()
        repos.addAll(newRepos)
        notifyDataSetChanged()
    }

}