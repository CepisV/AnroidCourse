package kz.step.jsonplaceholder.data.repository

import kz.step.jsonplaceholder.data.remote.MainApi
import kz.step.jsonplaceholder.domain.models.Comment
import kz.step.jsonplaceholder.domain.repository.CommentsRepository

class CommentsRepositoryImpl(private val mainApi: MainApi) : CommentsRepository {
    override suspend fun getPostComments(postId: Int): List<Comment> {
        return mainApi.getPostComments(postId)
    }
}