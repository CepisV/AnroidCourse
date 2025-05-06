package kz.step.jsonplaceholder.domain.use_case

import kz.step.jsonplaceholder.domain.models.User
import kz.step.jsonplaceholder.domain.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: Int) : User {
        return userRepository.getUser(userId)
    }
}