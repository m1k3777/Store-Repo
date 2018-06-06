package com.mvillasenor.storerrepo.ui.adapter

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mvillasenor.storerrepo.data.models.GithubRepo
import kotlinx.android.synthetic.main.repo_item.view.*


/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(githubRepo: GithubRepo) {
        itemView.name.text = githubRepo.name
        itemView.description.text = githubRepo.description
        itemView.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubRepo.url))
            view.context.startActivity(intent)
        }
    }

}