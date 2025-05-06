package kz.step.jsonplaceholder.domain.use_case

import kz.step.jsonplaceholder.domain.models.User
import kz.step.jsonplaceholder.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {

    suspend fun execute(): List<User> {
        return userRepository.getUsers()
    }
}
