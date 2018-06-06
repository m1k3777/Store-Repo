package com.mvillasenor.storerrepo.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Miguel Villaseñor on 6/6/18.
 */
data class GithubRepo(val id: Int, val name: String, val description: String, @SerializedName("html_url") val url: String)