package kz.step.jsonplaceholder.data.repository

import kz.step.jsonplaceholder.data.remote.MainApi
import kz.step.jsonplaceholder.domain.models.Post
import kz.step.jsonplaceholder.domain.repository.PostsRepository

class PostsRepositoryImpl(private val mainApi: MainApi) : PostsRepository {

    override suspend fun getPosts(): List<Post> {
        return mainApi.getPosts()
    }

    override suspend fun getPost(id: Int): Post {
        return mainApi.getPost(id)
    }

}