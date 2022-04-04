package com.magicapp.firebasekotlin.database

import com.magicapp.firebasekotlin.model.Post

interface DatabaseHandler {
    fun onSuccess(post: Post? = null, posts: ArrayList<Post> = ArrayList())
    fun onError()
}