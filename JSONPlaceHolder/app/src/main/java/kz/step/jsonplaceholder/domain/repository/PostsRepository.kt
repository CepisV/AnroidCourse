package kz.step.jsonplaceholder.domain.repository

import kz.step.jsonplaceholder.domain.models.Post

interface PostsRepository {
    suspend fun getPosts(): List<Post>

    suspend fun getPost(id: Int): Post
}