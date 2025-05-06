package kz.step.jsonplaceholder.domain.use_case

import kz.step.jsonplaceholder.domain.models.Post
import kz.step.jsonplaceholder.domain.repository.PostsRepository

class GetPostByIdUseCase(private val postsRepository: PostsRepository) {
    suspend operator fun invoke(id: Int): Post {
        return postsRepository.getPost(id)
    }
}