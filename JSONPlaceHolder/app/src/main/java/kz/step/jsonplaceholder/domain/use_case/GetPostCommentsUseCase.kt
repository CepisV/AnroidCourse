package kz.step.jsonplaceholder.domain.use_case

import kz.step.jsonplaceholder.domain.models.Comment
import kz.step.jsonplaceholder.domain.repository.CommentsRepository

private const val NO_LIMIT = -1

class GetPostCommentsUseCase(private val commentsRepository: CommentsRepository) {
    suspend operator fun invoke(postId: Int, limitBy: Int = NO_LIMIT): List<Comment> {
        if (limitBy == NO_LIMIT || limitBy < 0) {
            return commentsRepository.getPostComments(postId)
        }

        return commentsRepository.getPostComments(postId).take(limitBy)
    }

}