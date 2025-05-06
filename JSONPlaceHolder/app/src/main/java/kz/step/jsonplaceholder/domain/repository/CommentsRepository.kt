package kz.step.jsonplaceholder.domain.repository

import kz.step.jsonplaceholder.domain.models.Comment

interface CommentsRepository {
    suspend fun getPostComments(postId: Int): List<Comment>
}