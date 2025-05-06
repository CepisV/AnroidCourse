package kz.step.jsonplaceholder.domain.use_case

import kz.step.jsonplaceholder.domain.models.Post
import kz.step.jsonplaceholder.domain.repository.PostsRepository

class GetPostsUseCase(private val postsRepository: PostsRepository) {
    suspend operator fun invoke(): List<Post> {
        return postsRepository.getPosts()
    }
}